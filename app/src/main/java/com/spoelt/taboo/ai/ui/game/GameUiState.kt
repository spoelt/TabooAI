package com.spoelt.taboo.ai.ui.game

import com.spoelt.taboo.ai.domain.model.TabooCardData
import kotlinx.serialization.Serializable

data class GameUiState(
    val isLoading: Boolean = true,
    val players: List<PlayerData> = emptyList(),
    val totalCards: List<TabooCardData> = emptyList(),
    val isTransitioningBetweenPlayers: Boolean = false,
    val guessingTimeMillis: Long = 60_000L,
) {
    val currentPlayerIndex: Int
        get() = players.indexOfFirst { it.isCurrentlyPlaying }.takeIf { it >= 0 } ?: 0

    val currentPlayerName: String?
        get() = players.getOrNull(currentPlayerIndex)?.name

    val nextPlayerIndex: Int?
        get() = if (players.isEmpty()) {
            null
        } else {
            (currentPlayerIndex + 1) % players.size
        }

    val nextPlayerName: String?
        get() = nextPlayerIndex?.let { players.getOrNull(it)?.name }
}

@Serializable
data class PlayerData(
    val id: Int,
    val name: String,
    val points: Int = 0,
    val isCurrentlyPlaying: Boolean = false,
)