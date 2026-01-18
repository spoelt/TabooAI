package com.spoelt.taboo.ai

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.spoelt.taboo.ai.domain.model.HomeScreenButtonType
import com.spoelt.taboo.ai.ui.home.HomeScreen
import com.spoelt.taboo.ai.ui.nav.Game
import com.spoelt.taboo.ai.ui.nav.GameFinished
import com.spoelt.taboo.ai.ui.nav.HighScores
import com.spoelt.taboo.ai.ui.nav.Home
import com.spoelt.taboo.ai.ui.nav.Navigator
import com.spoelt.taboo.ai.ui.nav.Settings
import com.spoelt.taboo.ai.ui.nav.SetupGame
import com.spoelt.taboo.ai.ui.nav.rememberNavigationState
import com.spoelt.taboo.ai.ui.nav.toEntries
import com.spoelt.taboo.ai.ui.setupgame.SetuoGameScreen
import com.spoelt.taboo.ai.ui.theme.TabooTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.dark(Color.WHITE))

        setContent {
            val navigationState = rememberNavigationState(
                startRoute = Home,
                topLevelRoutes = setOf(
                    Home, // TODO adapt in future?
                )
            )
            val navigator = remember { Navigator(navigationState) }
            val entryProvider = entryProvider {
                // entry<RouteA>{ key -> ScreenA(title = "Screen has ID: ${key.id}") }
                entry<Home> {
                    HomeScreen(onHomeButtonClick = { type ->
                        val route = when (type) {
                            HomeScreenButtonType.NEW_GAME -> SetupGame
                            HomeScreenButtonType.HIGH_SCORES -> HighScores
                            HomeScreenButtonType.SETTINGS -> Settings
                        }
                        navigator.navigate(route)
                    })
                }
                entry<SetupGame> {
                    SetuoGameScreen(
                        onStartGame = {
                            navigator.navigate(Game)
                        }
                    )
                }
                entry<Game> {

                }
                entry<GameFinished> {

                }
                entry<HighScores> {

                }
                entry<Settings> {

                }
            }

            TabooTheme {
                NavDisplay(
                    entries = navigationState.toEntries(entryProvider),
                    onBack = { navigator.goBack() },
                )
            }
        }
    }
}