package com.example.circuler.presentation.type

import androidx.compose.ui.graphics.Color
import com.example.circuler.ui.theme.Green1
import com.example.circuler.ui.theme.Green4
import com.example.circuler.ui.theme.Yellow1

enum class ChipType(
    val text: String,
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color
) {
    IN_PROGRESS(
        text = "In-progress",
        backgroundColor = Green4,
        borderColor = Green1,
        textColor = Green1
    ),
    COMPLETED(
        text = "Completed",
        backgroundColor = Green1,
        borderColor = Green1,
        textColor = Yellow1
    )
}
