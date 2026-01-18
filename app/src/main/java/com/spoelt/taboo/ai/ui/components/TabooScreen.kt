package com.spoelt.taboo.ai.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun TabooScreen(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.taboo_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Transparent
                        )
                    )
                )
        ) {
            Scaffold(
                containerColor = Color.Transparent
            ) { innerPadding ->
                content(innerPadding)
            }
        }
    }
}

@Preview
@Composable
private fun TabooScreenPreview() {
    TabooTheme {
        TabooScreen(
            content = {}
        )
    }
}