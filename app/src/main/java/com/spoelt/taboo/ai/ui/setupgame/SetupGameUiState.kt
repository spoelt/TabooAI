package com.spoelt.taboo.ai.ui.setupgame

import com.spoelt.taboo.ai.domain.model.DifficultyLevel

data class SetupGameUiState(
    val numberOfPlayers: Int,
    val playerNames: List<String>,
    val difficulty: List<DifficultyLevel>,
    val isSetupComplete: Boolean,
)