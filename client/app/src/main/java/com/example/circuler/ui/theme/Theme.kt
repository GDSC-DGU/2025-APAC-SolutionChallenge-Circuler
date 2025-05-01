package com.example.circuler.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object CirculerTheme {
    val colors: CirculerColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CirculerTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypo.current
}

@Composable
fun ProvideCirculerColorsAndTypography(
    colors: CirculerColors,
    typography: CirculerTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypo provides typography,
        content = content
    )
}

@Composable
fun CirculerTheme(
    backgroundColor: Color = defaultCirculerColors.grayScale1,
    content: @Composable () -> Unit
) {
    ProvideCirculerColorsAndTypography(
        colors = defaultCirculerColors,
        typography = defaultCirculerTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = backgroundColor.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }

        MaterialTheme(
            content = content
        )
    }
}