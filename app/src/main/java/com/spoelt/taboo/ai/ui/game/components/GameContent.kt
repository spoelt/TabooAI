package com.spoelt.taboo.ai.ui.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.spoelt.taboo.ai.ui.components.LottieAnimation
import com.spoelt.taboo.ai.ui.components.TabooAnimatedVisibility
import com.spoelt.taboo.ai.ui.components.TabooButton
import com.spoelt.taboo.ai.ui.components.TabooScreen
import com.spoelt.taboo.ai.ui.game.GameEvent
import com.spoelt.taboo.ai.ui.game.GameUiState
import com.spoelt.taboo.ai.ui.game.PlayerData
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.Downriver
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun GameContent(
    modifier: Modifier = Modifier,
    uiState: GameUiState,
    onEvent: (GameEvent) -> Unit,
) {
    val showLoadingAnimation = uiState.isLoading || uiState.isTransitioningBetweenPlayers

    TabooScreen(
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                if (!uiState.isLoading) {
                    Column(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(Dimens.spacingM),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CurrentTurnInfo(
                            name = uiState.currentPlayerName,
                            guessingTime = uiState.guessingTimeMillis,
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        CardStack(
                            cards = uiState.totalCards,
                            onCardExplained = { card ->
                                onEvent(GameEvent.CardExplained(card))
                            },
                            onCardSkipped = { card ->
                                onEvent(GameEvent.CardSkipped(card))
                            },
                        )

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                TabooAnimatedVisibility(showLoadingAnimation) {
                    LoadingAnimation(
                        isTransitioning = uiState.isTransitioningBetweenPlayers,
                        playerName = uiState.nextPlayerName,
                    )
                }
            }
        },
        bottomBarContent = {
            if (!uiState.isLoading) {
                TabooAnimatedVisibility(!uiState.isTransitioningBetweenPlayers) {
                    TabooButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(horizontal = Dimens.spacingM)
                            .padding(bottom = Dimens.spacingS)
                            .imePadding(),
                        text = stringResource(R.string.end_game),
                        onClick = { onEvent(GameEvent.EndGame) },
                    )
                }
            }
        }
    )
}

@Composable
private fun LoadingAnimation(
    modifier: Modifier = Modifier,
    isTransitioning: Boolean,
    playerName: String?,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
                    .padding(Dimens.spacingM),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LottieAnimation(
                    modifier = Modifier.size(300.dp)
                )

                if (isTransitioning) {
                    playerName?.let { name ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (-48).dp),
                            text = stringResource(R.string.ready_player, name),
                            style = MaterialTheme.typography.titleLarge,
                            color = Downriver,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

private val previewUiState = GameUiState(
    isLoading = false,
    players = (1..3).map {
        PlayerData(
            id = it,
            name = "Player $it",
        )
    },
    totalCards = (1..10).map {
        TabooCardData(
            id = it,
            word = "Word $it",
            forbiddenWords = (1..5).map { num ->
                "Forbidden word $num"
            },
        )
    },
    isTransitioningBetweenPlayers = false,
)

@Preview(showSystemUi = true)
@Composable
private fun GameContentGamePlayPreview() {
    TabooTheme {
        GameContent(
            uiState = previewUiState,
            onEvent = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun GameContentTransitioningPreview() {
    TabooTheme {
        GameContent(
            uiState = previewUiState.copy(isTransitioningBetweenPlayers = true),
            onEvent = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun GameContentLoadingPreview() {
    TabooTheme {
        GameContent(
            uiState = previewUiState.copy(isLoading = true),
            onEvent = {}
        )
    }
}