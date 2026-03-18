package com.spoelt.taboo.ai.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TabooAnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    fadeOnly: Boolean = false,
    content: @Composable () -> Unit,
) {
    val enterTransition = if (fadeOnly) {
        fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
    } else {
        fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) +
                slideInVertically(
                    initialOffsetY = { it / 8 },
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                ) +
                expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                )
    }

    val exitTransition = if (fadeOnly) {
        fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
    } else {
        fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow)) +
                scaleOut(
                    targetScale = 0.92f,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                ) +
                slideOutVertically(
                    targetOffsetY = { it / 8 },
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                ) +
                shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                )
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enterTransition,
        exit = exitTransition,
    ) {
        content()
    }
}
