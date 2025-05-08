package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.type.DeliveryType
import com.example.circuler.presentation.type.PackagingType
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoBottomSheetItem(
    option: String,
    isActive: Boolean,
    activeBgColor: Color,
    activeContentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height((LocalConfiguration.current.screenHeightDp * 0.072).dp)
            .background(
                color = if (isActive) activeBgColor else CirculerTheme.colors.grayScale1
            )
            .noRippleClickable {
                onClick()
            }
            .padding(vertical = 10.dp, horizontal = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option,
            style = CirculerTheme.typography.title3M16.copy(
                color = if (isActive) activeContentColor else CirculerTheme.colors.black
            )
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun PackagingTypeContent(
    activeIndex: Int,
    onClick: (Int) -> Unit = {}
) {
    val options = PackagingType.entries.toTypedArray()

    options.forEachIndexed { index, option ->
        CirculoBottomSheetItem(
            option = option.text,
            isActive = activeIndex == index,
            activeBgColor = CirculerTheme.colors.green4,
            activeContentColor = CirculerTheme.colors.green1,
            onClick = { onClick(index) }

        )
    }
}

@Composable
fun DeliveryTypeContent(
    activeIndex: Int,
    onClick: (Int) -> Unit = {}
) {
    val options = DeliveryType.entries.toTypedArray()

    options.forEachIndexed { index, option ->
        CirculoBottomSheetItem(
            option = option.text,
            isActive = activeIndex == index,
            activeBgColor = CirculerTheme.colors.green4,
            activeContentColor = CirculerTheme.colors.green1,
            onClick = { onClick(index) }

        )
    }
}
