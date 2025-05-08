package com.example.circuler.presentation.ui.confirm.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.circuler.presentation.core.component.CirculoText

@Composable
fun CirculoTextGroup(
    modifier: Modifier = Modifier
) {
    CirculoText(
        title = "Packaging type",
        subTitle = "plastic"
    )
    CirculoText(
        title = "Quantity",
        subTitle = "5"
    )
    CirculoText(
        title = "Shop Location",
        subTitle = "223-123"
    )
    CirculoText(
        title = "Distance",
        subTitle = "5"
    )
}
