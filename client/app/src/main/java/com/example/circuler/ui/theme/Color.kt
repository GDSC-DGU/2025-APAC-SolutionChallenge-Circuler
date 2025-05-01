package com.example.circuler.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)

// Main Green
val Green1 = Color(0xFF328E6E)
val Green2 = Color(0xFF67AE6E)
val Green3 = Color(0xFF90C67C)
val Green4 = Color(0xFFE1EEBC)

// Yellow
val Yellow1 = Color(0xFFFAF8F3)

// Gray Scale
val GrayScale1 = Color(0xFFFFFFFF)
val GrayScale2 = Color(0xFFFAFAFA)
val GrayScale3 = Color(0xFFF4F4F4)
val GrayScale4 = Color(0xFFEDEDED)
val GrayScale5 = Color(0xFFD6D6D6)
val GrayScale6 = Color(0xFFBCBCBC)
val GrayScale7 = Color(0xFFA1A1A1)
val GrayScale8 = Color(0xFF858585)
val GrayScale9 = Color(0xFF696969)
val GrayScale10 = Color(0xFF4D4D4D)
val GrayScale11 = Color(0xFF323232)
val GrayScale12 = Color(0xFF1A1A1A)

@Immutable
data class CirculerColors(
    val black: Color,
    val green1: Color,
    val green2: Color,
    val green3: Color,
    val green4: Color,
    val yellow1: Color,
    val grayScale1: Color,
    val grayScale2: Color,
    val grayScale3: Color,
    val grayScale4: Color,
    val grayScale5: Color,
    val grayScale6: Color,
    val grayScale7: Color,
    val grayScale8: Color,
    val grayScale9: Color,
    val grayScale10: Color,
    val grayScale11: Color,
    val grayScale12: Color
)

val defaultCirculerColors = CirculerColors(
    black = Black,
    green1 = Green1,
    green2 = Green2,
    green3 = Green3,
    green4 = Green4,
    yellow1 = Yellow1,
    grayScale1 = GrayScale1,
    grayScale2 = GrayScale2,
    grayScale3 = GrayScale3,
    grayScale4 = GrayScale4,
    grayScale5 = GrayScale5,
    grayScale6 = GrayScale6,
    grayScale7 = GrayScale7,
    grayScale8 = GrayScale8,
    grayScale9 = GrayScale9,
    grayScale10 = GrayScale10,
    grayScale11 = GrayScale11,
    grayScale12 = GrayScale12
)

val LocalColors = staticCompositionLocalOf { defaultCirculerColors }
