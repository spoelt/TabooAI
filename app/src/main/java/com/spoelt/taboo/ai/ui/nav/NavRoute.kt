package com.spoelt.taboo.ai.ui.nav

import androidx.navigation3.runtime.NavKey
import com.spoelt.taboo.ai.ui.game.PlayerData
import kotlinx.serialization.Serializable

@Serializable data object Home : NavKey
@Serializable data object SetupGame : NavKey
@Serializable data class Game(val players: List<String>) : NavKey
@Serializable data class GameFinished(val players: List<PlayerData>) : NavKey
@Serializable data object HighScores : NavKey
@Serializable data object Settings : NavKey