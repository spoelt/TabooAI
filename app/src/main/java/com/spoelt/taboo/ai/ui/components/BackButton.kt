package com.spoelt.taboo.ai.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.ui.theme.TabooTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import com.spoelt.taboo.ai.ui.theme.Dimens

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    LiquidGlassCard(modifier = modifier.clickable { onClick() }) {
        Icon(
            modifier = Modifier.padding(Dimens.spacingS),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun BackButtonPreview() {
    TabooTheme {
        BackButton(onClick = {})
    }
}