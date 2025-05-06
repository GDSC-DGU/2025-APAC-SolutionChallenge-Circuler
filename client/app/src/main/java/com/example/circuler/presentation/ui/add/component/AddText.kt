package com.example.circuler.presentation.ui.add.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun AddTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(start = 20.dp, top = 24.dp, bottom = 60.dp),
        text = "Please Provide\n" +
            "packaging request information",
        style = CirculerTheme.typography.heading3M20,
        color = CirculerTheme.colors.grayScale12
    )
}

@Composable
fun AddSubTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = CirculerTheme.typography.body3M14,
        color = CirculerTheme.colors.grayScale12
    )
}
