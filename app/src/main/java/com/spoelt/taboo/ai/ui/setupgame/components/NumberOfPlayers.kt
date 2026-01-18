package com.spoelt.taboo.ai.ui.setupgame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.Downriver
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun NumberOfPlayers(
    modifier: Modifier = Modifier,
    numberOfPlayers: Int,
    onPlayersSelected: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingM),
    ) {
        Text(
            text = stringResource(R.string.number_of_players),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spacingS),
        ) {
            (1..4).forEach {
                if (numberOfPlayers == it) {
                    SelectedButton(text = "$it")
                } else {
                    UnselectedButton(
                        text = "$it",
                        onClick = { onPlayersSelected(it) },
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedButton(
    modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        modifier = modifier.size(64.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(Dimens.cornerSmall),
        onClick = {}, // do nothing - already selected
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun UnselectedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.size(64.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(Dimens.cornerSmall),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Downriver,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview
@Composable
private fun NumberOfPlayersPreview() {
    TabooTheme {
        NumberOfPlayers(
            numberOfPlayers = 2,
            onPlayersSelected = {}
        )
    }
}