package com.spoelt.taboo.ai.ui.setupgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoelt.taboo.ai.domain.model.DifficultyLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupGameViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(initUiState())
    val uiState = _uiState.asStateFlow()

    private val _startGame = MutableSharedFlow<Boolean>()
    val startGame = _startGame.asSharedFlow()

    private fun initUiState(): SetupGameUiState {
        val defaultPlayers = 2
        return SetupGameUiState(
            numberOfPlayers = defaultPlayers,
            playerNames = List(defaultPlayers) { "" },
            difficulty = listOf(DifficultyLevel.EASY), // TODO
            isSetupComplete = false,
        )
    }

    fun onEvent(event: SetupGameEvent) {
        when (event) {
            is SetupGameEvent.NumberOfPlayersSelected -> {
                updateNumberOfPlayers(event.count)
            }

            is SetupGameEvent.PlayerNameUpdated -> {
                updatePlayerName(
                    index = event.index,
                    name = event.name,
                )
            }

            SetupGameEvent.StartGame -> {
                startGame()
            }
        }
    }

    private fun updateNumberOfPlayers(count: Int) {
        _uiState.update { state ->
            val updatedNames = List(count) { index ->
                state.playerNames.getOrNull(index).orEmpty()
            }

            state.copy(
                numberOfPlayers = count,
                playerNames = updatedNames,
                isSetupComplete = updatedNames.none { it.isBlank() }
            )
        }
    }

    private fun updatePlayerName(index: Int, name: String) {
        _uiState.update { state ->
            val updatedNames = state.playerNames.mapIndexed { idx, currentName ->
                if (idx == index) name else currentName
            }

            state.copy(
                playerNames = updatedNames,
                isSetupComplete = updatedNames.none { it.isBlank() }
            )
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            _startGame.emit(true)
        }
    }
}