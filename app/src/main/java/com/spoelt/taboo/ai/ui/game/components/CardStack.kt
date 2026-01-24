package com.spoelt.taboo.ai.ui.game.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spartapps.swipeablecards.state.rememberSwipeableCardsState
import com.spartapps.swipeablecards.ui.SwipeableCardDirection
import com.spartapps.swipeablecards.ui.SwipeableCardsProperties
import com.spartapps.swipeablecards.ui.lazy.LazySwipeableCards
import com.spartapps.swipeablecards.ui.lazy.items
import com.spoelt.taboo.ai.domain.model.TabooCardData
import com.spoelt.taboo.ai.ui.theme.Dimens

@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    cards: List<TabooCardData>,
    onCardExplained: (TabooCardData) -> Unit,
    onCardSkipped: (TabooCardData) -> Unit,
) {
    val state = rememberSwipeableCardsState(
        initialCardIndex = 0,
        itemCount = { cards.size }
    )

    LazySwipeableCards(
        modifier = modifier
            .padding(top = Dimens.spacingM)
            .fillMaxWidth()
            .padding(end = Dimens.spacingM),
        state = state,
        onSwipe = { card, direction ->
            when (direction) {
                SwipeableCardDirection.Right -> onCardExplained(card)
                SwipeableCardDirection.Left -> onCardSkipped(card)
            }
        },
        properties = SwipeableCardsProperties(
            stackedCardsOffset = 56.dp,
        )
    ) {
        items(cards) { card, _, _ ->
            TabooCard(
                card = card,
            )
        }
    }
}