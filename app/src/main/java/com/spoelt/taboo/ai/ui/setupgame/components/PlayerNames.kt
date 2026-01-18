package com.spoelt.taboo.ai.ui.setupgame.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.spoelt.taboo.ai.R
import com.spoelt.taboo.ai.ui.theme.Dimens
import com.spoelt.taboo.ai.ui.theme.Downriver
import com.spoelt.taboo.ai.ui.theme.TabooTheme

@Composable
fun PlayerNames(
    modifier: Modifier = Modifier,
    numberOfPlayers: Int,
    names: List<String>,
    onNameUpdated: (Int, String) -> Unit,
    onStartGame: () -> Unit,
) {
    val showPlayerField: (Int) -> Boolean = { index -> numberOfPlayers - 1 >= index }
    val playerNameAt: (Int) -> String = { index -> names.getOrNull(index).orEmpty() }
    fun nextVisibleIndex(current: Int): Int? =
        ((current + 1)..3).firstOrNull { showPlayerField(it) }
    val focusRequesters = remember {
        List(4) { FocusRequester() }
    }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(numberOfPlayers) {
        focusRequesters.firstOrNull()?.requestFocus()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingM),
    ) {
        Text(
            text = stringResource(R.string.names_of_players),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.spacingS),
        ) {
            // max 4 players for now
            (0..3).forEach { index ->
                val isVisible = showPlayerField(index)
                val nextIndex = nextVisibleIndex(index)

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequesters[index]),
                        value = playerNameAt(index),
                        onValueChange = { name -> onNameUpdated(index, name) },
                        label = {
                            Text(
                                text = stringResource(R.string.player_label, index + 1),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            autoCorrectEnabled = false,
                            imeAction = if (nextIndex != null) ImeAction.Next else ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                nextIndex?.let { focusRequesters[it].requestFocus() }
                            },
                            onDone = {
                                focusManager.clearFocus()
                                onStartGame()
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Downriver,
                            focusedContainerColor = Downriver,
                            disabledContainerColor = Downriver,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlayerNamesPreview() {
    TabooTheme {
        PlayerNames(
            numberOfPlayers = 2,
            names = (1..2).map {
                "Player $it"
            },
            onNameUpdated = { _, _ -> },
            onStartGame = {},
        )
    }
}