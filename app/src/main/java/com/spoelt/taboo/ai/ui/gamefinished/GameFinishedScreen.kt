package com.spoelt.taboo.ai.ui.gamefinished

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.components.TabooPrimaryButton
import com.spoelt.taboo.ai.ui.components.TabooScreen
import com.spoelt.taboo.ai.ui.game.PlayerData
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun GameFinishedScreen(
    modifier: Modifier = Modifier,
    players: List<PlayerData>,
    onGoBack: () -> Unit,
) {
    val winner = remember(players) {
        players.maxBy { it.points }.name
    }

    TabooScreen(
        content = {
            Column(
                modifier = modifier
                    .safeDrawingPadding()
                    .padding(Dimens.spacingM)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Spielstand",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )

                players.forEach { player ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.spacingM),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = player.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "${player.points} Punkte",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.spacingXXL),
                    text = "$winner GEWINNT",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
            }
        },
        bottomBarContent = {
            TabooPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Dimens.spacingM)
                    .padding(bottom = Dimens.spacingXS)
                    .imePadding(),
                text = stringResource(R.string.cancel),
                onClick = { onGoBack() },
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun GameFinishedScreenPreview() {
    TabooTheme {
        GameFinishedScreen(
            players = (1..3).map {
                PlayerData(
                    id = it,
                    name = "Player $it",
                    points = it,
                )
            },
            onGoBack = {},
        )
    }
}