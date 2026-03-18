package com.spoelt.taboo.ai.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.spoelt.taboo.ai.domain.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: WordRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var guessingTimerJob: Job? = null

    private val _endGame = MutableSharedFlow<Boolean>()
    val endGame = _endGame.asSharedFlow()

    fun initWithDataAndLoad(players: List<String>) {
        _uiState.update { state ->
            state.copy(
                players = (0..<players.size).map {
                    PlayerData(
                        id = it,
                        name = players[it],
                        points = 0,
                        isCurrentlyPlaying = it == 0,
                    )
                },
            )
        }
        loadWords()
    }

    private fun loadWords() {
        viewModelScope.launch {
            repository.loadWords()
                .onStart {
                    // fake delay to give player time to get ready
                    delay(2_000)
                }
                .collect { words ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            totalCards = words.shuffled(),
                        )
                    }
                    startGuessingTimer()
                }
        }
    }

    private fun startGuessingTimer(totalMillis: Long = 60_000L) {
        guessingTimerJob?.cancel()
        guessingTimerJob = viewModelScope.launch {
            val startTime = System.currentTimeMillis()

            while (isActive) {
                val elapsed = System.currentTimeMillis() - startTime
                val remaining = (totalMillis - elapsed).coerceAtLeast(0L)

                _uiState.update { state ->
                    state.copy(guessingTimeMillis = remaining)
                }

                if (remaining == 0L) {
                    onEvent(GameEvent.TimeRunOut)
                    break
                }

                delay(100L)
            }
        }
    }

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.CardExplained -> {
                _uiState.update { state ->
                    state.copy(
                        currentCardIndex = state.currentCardIndex + 1,
                        players = state.players.mapIndexed { index, player ->
                            if (index == state.currentPlayerIndex) {
                                player.copy(points = player.points + 1)
                            } else {
                                player
                            }
                        },
                    )
                }

                if (isLastCard(event.card)) endGame()
            }

            is GameEvent.CardSkipped -> {
                _uiState.update { state ->
                    state.copy(currentCardIndex = state.currentCardIndex + 1)
                }
                if (isLastCard(event.card)) endGame()
            }

            GameEvent.TimeRunOut -> {
                guessingTimerJob?.cancel()

                viewModelScope.launch {
                    val wasLastCard = _uiState.value.currentCardIndex == _uiState.value.totalCards.size - 1

                    _uiState.update { state ->
                        state.copy(
                            isTransitioningBetweenPlayers = true,
                            guessingTimeMillis = 0L,
                        )
                    }

                    if (wasLastCard) {
                        endGame()
                        return@launch
                    }

                    delay(TRANSITION_TIME)

                    _uiState.update { state ->
                        val updatedPlayers = state.players.rotateCurrentPlayer()
                        state.copy(
                            players = updatedPlayers,
                            currentCardIndex = state.currentCardIndex + 1,
                            stackResetKey = state.stackResetKey + 1,
                            isTransitioningBetweenPlayers = false,
                        )
                    }

                    startGuessingTimer()
                }
            }

            GameEvent.EndGame -> {
                endGame()
            }
        }
    }

    private fun isLastCard(card: TabooCardData): Boolean {
        val cards = _uiState.value.totalCards
        val currentIndex = cards.indexOf(card)
        return currentIndex != -1 && currentIndex == cards.size - 1
    }

    private fun List<PlayerData>.rotateCurrentPlayer(): List<PlayerData> {
        if (isEmpty()) return this

        val currentIndex = _uiState.value.currentPlayerIndex
        val nextIndex = (currentIndex + 1) % size

        return mapIndexed { index, player ->
            when (index) {
                currentIndex -> player.copy(isCurrentlyPlaying = false)
                nextIndex -> player.copy(isCurrentlyPlaying = true)
                else -> player
            }
        }
    }

    fun endGame() {
        viewModelScope.launch {
            _endGame.emit(true)
        }
    }

    companion object {
        private const val TRANSITION_TIME = 5_000L
    }
}
