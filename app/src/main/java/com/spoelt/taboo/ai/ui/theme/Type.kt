package com.spoelt.taboo.ai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.spoelt.taboo.ai.R

val HeaderFont = FontFamily(
    Font(R.font.kranky_regular, FontWeight.Bold),
)

val BodyFont = FontFamily(
    Font(R.font.outfit_black, FontWeight.Black),
    Font(R.font.outfit_extra_bold, FontWeight.ExtraBold),
    Font(R.font.outfit_bold, FontWeight.Bold),
    Font(R.font.outfit_semi_bold, FontWeight.SemiBold),
    Font(R.font.outfit_medium, FontWeight.Medium),
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_light, FontWeight.Light),
    Font(R.font.outfit_extra_light, FontWeight.ExtraLight),
    Font(R.font.outfit_thin, FontWeight.Thin),
)

val TabooTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontDisplayLarge,
        lineHeight = Dimens.lineHeightDisplayLarge,
        letterSpacing = Dimens.letterSpacingDisplayLarge
    ),
    displayMedium = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontDisplayMedium,
        lineHeight = Dimens.lineHeightDisplayMedium,
        letterSpacing = Dimens.letterSpacingDisplayMedium
    ),
    displaySmall = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontDisplaySmall,
        lineHeight = Dimens.lineHeightDisplaySmall,
        letterSpacing = Dimens.letterSpacingDisplaySmall
    ),
    headlineLarge = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontHeadlineLarge,
        lineHeight = Dimens.lineHeightHeadlineLarge,
        letterSpacing = Dimens.letterSpacingHeadlineLarge
    ),
    headlineMedium = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontHeadlineMedium,
        lineHeight = Dimens.lineHeightHeadlineMedium,
        letterSpacing = Dimens.letterSpacingHeadlineMedium
    ),
    headlineSmall = TextStyle(
        fontFamily = HeaderFont,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.fontHeadlineSmall,
        lineHeight = Dimens.lineHeightHeadlineSmall,
        letterSpacing = Dimens.letterSpacingHeadlineSmall
    ),
    titleLarge = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens.fontTitleLarge,
        lineHeight = Dimens.lineHeightTitleLarge,
        letterSpacing = Dimens.letterSpacingTitleLarge
    ),
    titleMedium = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.fontTitleMedium,
        lineHeight = Dimens.lineHeightTitleMedium,
        letterSpacing = Dimens.letterSpacingTitleMedium
    ),
    titleSmall = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.fontTitleSmall,
        lineHeight = Dimens.lineHeightTitleSmall,
        letterSpacing = Dimens.letterSpacingTitleSmall
    ),
    bodyLarge = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.fontBodyLarge,
        lineHeight = Dimens.lineHeightBodyLarge,
        letterSpacing = Dimens.letterSpacingBodyLarge
    ),
    bodyMedium = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.fontBodyMedium,
        lineHeight = Dimens.lineHeightBodyMedium,
        letterSpacing = Dimens.letterSpacingBodyMedium
    ),
    bodySmall = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Light,
        fontSize = Dimens.fontBodySmall,
        lineHeight = Dimens.lineHeightBodySmall,
        letterSpacing = Dimens.letterSpacingBodySmall
    ),
    labelLarge = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.fontLabelLarge,
        lineHeight = Dimens.lineHeightLabelLarge,
        letterSpacing = Dimens.letterSpacingLabelLarge
    ),
    labelMedium = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.fontLabelMedium,
        lineHeight = Dimens.lineHeightLabelMedium,
        letterSpacing = Dimens.letterSpacingLabelMedium
    ),
    labelSmall = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.fontLabelSmall,
        lineHeight = Dimens.lineHeightLabelSmall,
        letterSpacing = Dimens.letterSpacingLabelSmall
    )
)