package com.spoelt.taboo.ai

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.spoelt.taboo.ai.domain.HomeScreenButtonType
import com.spoelt.taboo.ai.ui.components.TabooScreen
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
                    TabooScreen {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "SetupGame",
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
                entry<Game> {
                    TabooScreen {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Game",
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
                entry<GameFinished> {
                    TabooScreen {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "GameFinished",
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
                entry<HighScores> {
                    TabooScreen {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "HighScores",
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
                entry<Settings> {
                    TabooScreen {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Settings",
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
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