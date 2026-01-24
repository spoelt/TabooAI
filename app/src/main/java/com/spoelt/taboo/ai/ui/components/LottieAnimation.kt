package com.spoelt.taboo.ai.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
    asset: String = "lottie_loading.lottie",
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(asset)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
        speed = 0.75f,
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}