package com.spoelt.taboo.ai.ui.setupgame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.components.BackButton
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
import com.spoelt.taboo.ai.ui.components.TabooPrimaryButton
import com.spoelt.taboo.ai.ui.components.TabooScreen
import com.spoelt.taboo.ai.ui.setupgame.SetupGameEvent
import com.spoelt.taboo.ai.ui.setupgame.SetupGameUiState
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun SetupGameContent(
    modifier: Modifier = Modifier,
    uiState: SetupGameUiState,
    onEvent: (SetupGameEvent) -> Unit,
    onBackPressed: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val imeInsets = WindowInsets.ime
    val imePaddingBottom = with(LocalDensity.current) {
        imeInsets.getBottom(this).toDp()
    }
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    TabooScreen(
        modifier = modifier,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = statusBarPadding + Dimens.spacingM,
                    bottom = innerPadding.calculateBottomPadding() + navBarPadding + Dimens.spacingM + imePaddingBottom,
                    start = Dimens.spacingM,
                    end = Dimens.spacingM,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingM),
            ) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        BackButton(
                            onClick = {
                                focusManager.clearFocus()
                                onBackPressed()
                            }
                        )
                    }
                }
                item {
                    LiquidGlassCard(modifier = Modifier.fillMaxWidth()) {
                        SetupGameHeader()
                    }
                }

                item {
                    LiquidGlassCard(modifier = Modifier.fillMaxWidth()) {
                        NumberOfPlayers(
                            modifier = Modifier.padding(
                                horizontal = Dimens.spacingM,
                                vertical = Dimens.spacingL,
                            ),
                            numberOfPlayers = uiState.numberOfPlayers,
                            onPlayersSelected = { number ->
                                focusManager.clearFocus()
                                onEvent(SetupGameEvent.NumberOfPlayersSelected(number))
                            }
                        )
                    }
                }

                item {
                    LiquidGlassCard(modifier = Modifier.fillMaxWidth()) {
                        PlayerNames(
                            modifier = Modifier.padding(
                                horizontal = Dimens.spacingM,
                                vertical = Dimens.spacingL,
                            ),
                            numberOfPlayers = uiState.numberOfPlayers,
                            names = uiState.playerNames,
                            onNameUpdated = { index, name ->
                                onEvent(
                                    SetupGameEvent.PlayerNameUpdated(
                                        index = index,
                                        name = name,
                                    )
                                )
                            },
                            onStartGame = {
                                if (uiState.isSetupComplete) {
                                    focusManager.clearFocus()
                                    onEvent(SetupGameEvent.StartGame)
                                }
                            }
                        )
                    }
                }
            }
        },
        bottomBarContent = {
            TabooPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Dimens.spacingM)
                    .padding(bottom = Dimens.spacingXS),
                text = stringResource(R.string.start_game),
                enabled = uiState.isSetupComplete,
                onClick = {
                    focusManager.clearFocus()
                    onEvent(SetupGameEvent.StartGame)
                }
            )
        }
    )
}

@Composable
private fun SetupGameHeader(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.spacingM),
        text = stringResource(R.string.setup_game_title),
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SetupGameContentEmptyPreview() {
    TabooTheme {
        SetupGameContent(
            uiState = SetupGameUiState(
                numberOfPlayers = 2,
                playerNames = listOf(),
                isSetupComplete = false,
            ),
            onEvent = {},
            onBackPressed = {},
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SetupGameContentFilledPreview() {
    TabooTheme {
        SetupGameContent(
            uiState = SetupGameUiState(
                numberOfPlayers = 3,
                playerNames = (1..3).map {
                    "Player $it"
                },
                isSetupComplete = true,
            ),
            onEvent = {},
            onBackPressed = {},
        )
    }
}