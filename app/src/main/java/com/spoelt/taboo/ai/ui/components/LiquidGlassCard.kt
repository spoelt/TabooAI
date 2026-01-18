package com.spoelt.taboo.ai.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spoelt.taboo.ai.ui.theme.Dimens

@Composable
fun LiquidGlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.cornerLarge),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevation8
        ),
        colors = CardDefaults.cardColors(
            // "liquid glass" look
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.65f)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.White.copy(alpha = 0.25f)
        )
    ) {
        content()
    }
}