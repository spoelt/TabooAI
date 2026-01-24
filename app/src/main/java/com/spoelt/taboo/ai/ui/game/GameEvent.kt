package com.spoelt.taboo.ai.ui.game

import com.spoelt.taboo.ai.domain.model.TabooCardData

sealed class GameEvent {

    data class CardSkipped(val card: TabooCardData) : GameEvent()

    data class CardExplained(val card: TabooCardData) : GameEvent()

    data object TimeRunOut : GameEvent()

    data object EndGame : GameEvent()
}