package com.spoelt.taboo.ai.ui.game.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.data.util.formatTimeLeft
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
import com.spoelt.taboo.ai.ui.theme.Dimens

@Composable
fun CurrentTurnInfo(
    modifier: Modifier = Modifier,
    name: String?,
    guessingTime: Long,
) {
    val formattedTime = remember(guessingTime) {
        formatTimeLeft(guessingTime)
    }

    LiquidGlassCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(Dimens.spacingM)) {
                Text(
                    text = stringResource(R.string.player_info_title),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            AnimatedVisibility(guessingTime > 0L) {
                Text(
                    modifier = Modifier.padding(Dimens.spacingM),
                    text = formattedTime,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}