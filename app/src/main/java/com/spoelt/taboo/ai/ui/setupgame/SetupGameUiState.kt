package com.spoelt.taboo.ai.ui.setupgame

data class SetupGameUiState(
    val numberOfPlayers: Int,
    val playerNames: List<String>,
    val isSetupComplete: Boolean,
)