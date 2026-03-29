package com.spoelt.taboo.ai.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.domain.model.HomeScreenButtonType
import com.spoelt.taboo.ai.ui.components.LiquidGlassCard
import com.spoelt.taboo.ai.ui.components.TabooScreen
import com.spoelt.taboo.ai.ui.home.components.HomeHeader
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onHomeButtonClick: (HomeScreenButtonType) -> Unit,
) {
    val homeButtonTypes = listOf(
        HomeScreenButtonType.NEW_GAME,
        HomeScreenButtonType.HIGH_SCORES,
        HomeScreenButtonType.SETTINGS,
    )

    TabooScreen(
        modifier = modifier,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    top = WindowInsets.statusBars.asPaddingValues()
                        .calculateTopPadding() + Dimens.spacingS,
                    bottom = WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding() + Dimens.spacingM,
                    start = Dimens.spacingM,
                    end = Dimens.spacingM,
                ),
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingL),
            ) {
                item {
                    HomeHeader(
                        onLightningRoundClick = {
                            // TODO
                        },
                        onSettingsClick = {
                            // TODO
                        }
                    )
                }

                item {
                    LiquidGlassCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(Dimens.spacingL),
                            verticalArrangement = Arrangement.spacedBy(Dimens.spacingM),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            homeButtonTypes.forEach { type ->
                                HomeScreenButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    type = type,
                                    onClick = { onHomeButtonClick(type) },
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun HomeScreenButton(
    modifier: Modifier = Modifier,
    type: HomeScreenButtonType,
    onClick: () -> Unit,
) {
    val textResId = remember(type) {
        when (type) {
            HomeScreenButtonType.NEW_GAME -> R.string.new_game
            HomeScreenButtonType.HIGH_SCORES -> R.string.high_scores
            HomeScreenButtonType.SETTINGS -> R.string.settings
        }
    }

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.cornerMedium),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = Dimens.elevation12),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(Dimens.spacingS),
            text = stringResource(textResId),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    TabooTheme {
        HomeScreen(
            onHomeButtonClick = {},
        )
    }
}