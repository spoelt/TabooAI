package com.spoelt.taboo.ai.ui.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable data object Home : NavKey
@Serializable data object SetupGame : NavKey
@Serializable data object Game : NavKey
@Serializable data object GameFinished : NavKey
@Serializable data object HighScores : NavKey
@Serializable data object Settings : NavKey