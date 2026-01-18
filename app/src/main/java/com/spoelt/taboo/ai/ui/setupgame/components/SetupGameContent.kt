package com.spoelt.taboo.ai.ui.setupgame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.domain.model.DifficultyLevel
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
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
) {
    TabooScreen(
        modifier = modifier,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(Dimens.spacingM),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingM),
            ) {
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
                                    onEvent(SetupGameEvent.StartGame)
                                }
                            }
                        )
                    }
                }
            }
        },
        bottomBarContent = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Dimens.spacingM)
                    .padding(bottom = Dimens.spacingXS)
                    .imePadding(),
                shape = RoundedCornerShape(Dimens.cornerMedium),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = Dimens.elevation12),
                onClick = { onEvent(SetupGameEvent.StartGame) },
                enabled = uiState.isSetupComplete,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.DarkGray,
                )
            ) {
                Text(
                    modifier = Modifier.padding(Dimens.spacingS),
                    text = stringResource(R.string.start_game),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                )
            }
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
                difficulty = listOf(DifficultyLevel.EASY),
                isSetupComplete = false,
            ),
            onEvent = {},
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
                difficulty = listOf(DifficultyLevel.MEDIUM),
                isSetupComplete = true,
            ),
            onEvent = {},
        )
    }
}