package com.example.circuler.presentation.ui.confirm.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun ConfirmTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(start = 20.dp, top = 24.dp, bottom = 60.dp),
        text = "Please check \n" +
                "if the selected packaging is correct",
        style = CirculerTheme.typography.heading3M20,
        color = CirculerTheme.colors.grayScale12
    )
}