package com.spoelt.taboo.ai.ui.setupgame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spoelt.taboo.ai.ui.setupgame.components.SetupGameContent

@Composable
fun SetuoGameScreen(
    modifier: Modifier = Modifier,
    viewModel: SetupGameViewModel = hiltViewModel(),
    onStartGame: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.startGame.collect { shouldStart ->
            if (shouldStart) onStartGame()
        }
    }

    SetupGameContent(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}