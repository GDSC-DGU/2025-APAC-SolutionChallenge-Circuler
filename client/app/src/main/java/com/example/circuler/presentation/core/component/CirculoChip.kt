package com.example.circuler.presentation.core.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.type.ChipType
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoChip(
    chipType: ChipType,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Text(
        modifier = modifier
            .width((LocalConfiguration.current.screenWidthDp * 0.181).dp)
            .roundedBackgroundWithBorder(
                cornerRadius = 7.dp,
                backgroundColor = chipType.backgroundColor,
                borderColor = chipType.borderColor,
                borderWidth = 1.dp
            )
            .noRippleClickable {
                onClick()
            }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        text = chipType.text,
        style = CirculerTheme.typography.body4R12,
        color = chipType.textColor,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CirculoChipPreview() {
    CirculerTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)
        ) {

            CirculoChip(
                chipType = ChipType.MATCHING
            )
            CirculoChip(
                chipType = ChipType.PENDING
            )
            CirculoChip(
                chipType = ChipType.DELIVERING
            )
            CirculoChip(
                chipType = ChipType.DELIVERED
            )
        }
    }
}