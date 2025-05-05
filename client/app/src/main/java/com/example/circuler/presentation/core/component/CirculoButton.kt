package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    backgroundColor: Color = CirculerTheme.colors.green1,
    textColor: Color = CirculerTheme.colors.grayScale1,
    enabledColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    verticalPadding: Dp = 18.dp,
    textStyle: TextStyle = CirculerTheme.typography.title2Sb16
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = textStyle,
        color = textColor,
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 10.dp,
                backgroundColor = if (isEnabled) backgroundColor else enabledColor,
                borderColor = borderColor,
                borderWidth = borderWidth
            )
            .run {
                if (isEnabled) clickable(onClick = onClick) else this
            }
            .padding(vertical = verticalPadding)
    )
}

@Preview(showBackground = true)
@Composable
fun CirculoButtonPreview() {
    CirculerTheme {
        Column(
            modifier = Modifier
                .background(CirculerTheme.colors.green4),
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)
        ) {
            CirculoButton(
                text = "submit",
                onClick = {}
            )

            CirculoButton(
                text = "Yes, it’s correct",
                onClick = {}
            )

            CirculoButton(
                text = "delivery request",
                onClick = {}
            )
        }
    }
}