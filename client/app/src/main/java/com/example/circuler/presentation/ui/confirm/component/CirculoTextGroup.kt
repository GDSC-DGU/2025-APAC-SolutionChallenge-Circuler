package com.example.circuler.presentation.ui.confirm.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.presentation.core.component.CirculoText

@Composable
fun CirculoTextGroup(
    packageListCardEntity: PackageListCardEntity,
    modifier: Modifier = Modifier
) {
    CirculoText(
        title = "Packaging type",
        subTitle = packageListCardEntity.type
    )
    CirculoText(
        title = "Quantity",
        subTitle = packageListCardEntity.quantity.toString()
    )
    CirculoText(
        title = "Shop Location",
        subTitle = packageListCardEntity.location
    )
    CirculoText(
        title = "Distance",
        subTitle = "386.3m"
    )
}
