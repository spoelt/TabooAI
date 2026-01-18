package com.spoelt.taboo.ai.ui.setupgame

sealed class SetupGameEvent {
    data class NumberOfPlayersSelected(val count: Int) : SetupGameEvent()
    data class PlayerNameUpdated(val index: Int, val name: String) : SetupGameEvent()
    object StartGame : SetupGameEvent()
}