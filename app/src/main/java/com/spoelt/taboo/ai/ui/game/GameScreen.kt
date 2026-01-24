package com.spoelt.taboo.ai.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spoelt.taboo.ai.ui.game.components.GameContent

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    players: List<String>,
    viewModel: GameViewModel = hiltViewModel(),
    onEndGame: (List<PlayerData>) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initWithDataAndLoad(players)
    }

    LaunchedEffect(Unit) {
        viewModel.endGame.collect { shouldEnd ->
            if (shouldEnd) onEndGame(uiState.players)
        }
    }

    GameContent(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}