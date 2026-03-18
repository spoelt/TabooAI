package com.spoelt.taboo.ai.ui.game.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.data.util.formatTimeLeft
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
import com.spoelt.taboo.ai.ui.components.TabooAnimatedVisibility
import com.spoelt.taboo.ai.ui.theme.AlizarinCrimson
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

    val isLowTime = guessingTime in 1..10_000L

    val infiniteTransition = rememberInfiniteTransition(label = "PulseTransition")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isLowTime) 1.2f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    val timerColor by animateColorAsState(
        targetValue = if (isLowTime) AlizarinCrimson else MaterialTheme.colorScheme.onSurface,
        label = "TimerColor"
    )

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

            TabooAnimatedVisibility(
                visible = guessingTime > 0L,
                fadeOnly = true,
            ) {
                Text(
                    modifier = Modifier
                        .padding(Dimens.spacingM)
                        .scale(pulseScale),
                    text = formattedTime,
                    style = MaterialTheme.typography.titleLarge,
                    color = timerColor,
                )
            }
        }
    }
}
