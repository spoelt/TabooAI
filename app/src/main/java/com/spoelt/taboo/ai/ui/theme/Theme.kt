package com.spoelt.taboo.ai.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

private val DarkColorScheme = darkColorScheme(
    primary = TrueV,
    onPrimary = Color.White,

    secondary = Azure,
    onSecondary = Color.White,

    tertiary = Victoria,
    onTertiary = Color.White,

    background = Downriver,
    onBackground = Color(0xFFEAF0FF),

    surface = Color(0xFF0E355A),
    onSurface = Color(0xFFEAF0FF),

    surfaceVariant = Color(0xFF1A3F66),
    onSurfaceVariant = Color(0xFFD6E2FF),

    outline = Color(0xFF6F85B3)
)

@Composable
fun TabooTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    SideEffect {
        (view.context as? Activity)?.window?.let { window ->
            WindowInsetsControllerCompat(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = TabooTypography,
        content = content,
    )
}