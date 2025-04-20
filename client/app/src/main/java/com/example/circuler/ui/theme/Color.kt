package com.example.circuler.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

@Immutable
data class CirculerColors(
    val White: Color,
    val Black: Color,
)

val defaultCirculerColors = CirculerColors(
    White = White,
    Black = Black
)

val LocalColors = staticCompositionLocalOf { defaultCirculerColors }