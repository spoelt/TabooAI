package com.spoelt.taboo.ai.ui.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
import com.spoelt.taboo.ai.ui.theme.Dimens

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onLightningRoundClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    LiquidGlassCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(horizontal = Dimens.spacingS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onLightningRoundClick,
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null, // TODO add desc
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimens.spacingM),
                text = stringResource(R.string.home_screen_title),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            IconButton(
                onClick = onSettingsClick,
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = null, // TODO add desc
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}