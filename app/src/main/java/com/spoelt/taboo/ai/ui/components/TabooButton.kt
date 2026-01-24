package com.spoelt.taboo.ai.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun TabooButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.cornerMedium),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = Dimens.elevation12),
        onClick = { onClick() },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray,
        )
    ) {
        Text(
            modifier = Modifier.padding(Dimens.spacingS),
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview
@Composable
private fun TabooButtonPreview() {
    TabooTheme {
        TabooButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.start_game),
            onClick = {},
        )
    }
}