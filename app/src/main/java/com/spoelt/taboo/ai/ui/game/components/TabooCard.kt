package com.spoelt.taboo.ai.ui.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun TabooCard(
    modifier: Modifier = Modifier,
    card: TabooCardData,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.714f),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(Dimens.spacingM)
                .clip(MaterialTheme.shapes.medium)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFEB3B),
                            Color(0xFFF44336)
                        )
                    )
                )
                .padding(horizontal = Dimens.spacingM)
                .padding(top = Dimens.spacingM, bottom = Dimens.spacingS),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = card.word.uppercase(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        hyphens = Hyphens.Auto
                    ),
                    letterSpacing = 1.sp,
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = Dimens.spacingS)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                        .padding(Dimens.spacingM),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.spacingS)
                ) {
                    card.forbiddenWords.forEach { word ->
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = word,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    hyphens = Hyphens.Auto,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                letterSpacing = 1.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TabooCardPreview() {
    TabooTheme {
        TabooCard(
            modifier = Modifier.size(height = 420.dp, width = 310.dp),
            card = TabooCardData(
                id = 1,
                word = "Apfel",
                forbiddenWords = listOf("Baum", "rot", "rund", "knackig", "Obst"),
            ),
        )
    }
}