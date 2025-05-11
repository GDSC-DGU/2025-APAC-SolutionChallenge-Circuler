package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.domain.entity.DeliveryEntity
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.entity.PackageMyEntity
import com.example.circuler.presentation.core.extension.customShadow
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.type.ChipType
import com.example.circuler.ui.theme.CirculerTheme

// todo: 전체적인 icon 변경

@Composable
fun CirculoListCard(
    listCardEntity: PackageListCardEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale2,
                ambientColor = CirculerTheme.colors.grayScale2
            )
            .noRippleClickable {
                onClick()
            }
            .padding(start = 15.dp, top = 30.dp, bottom = 30.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)
        ) {
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Packaging type",
                subTitle = listCardEntity.type
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Quantity",
                subTitle = listCardEntity.quantity.toString()
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Shop Location",
                subTitle = listCardEntity.location
            )
        }
    }
}

@Composable
fun CirculoDeliveryListCard(
    deliveryEntity: DeliveryEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale2,
                ambientColor = CirculerTheme.colors.grayScale2
            )
            .noRippleClickable {
                onClick()
            }
            .padding(start = 15.dp, top = 30.dp, bottom = 30.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)
        ) {
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Packaging type",
                subTitle = deliveryEntity.packagingType
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Quantity",
                subTitle = deliveryEntity.submissionQuantity.toString()
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Shop Location",
                subTitle = deliveryEntity.requestLocation
            )
        }
    }
}

@Composable
fun CirculoListCardWithMethod(
    packageMyEntity: PackageMyEntity,
    chipString: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale5,
                ambientColor = CirculerTheme.colors.grayScale5
            )
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .noRippleClickable {
                onClick()
            }
            .padding(horizontal = 15.dp, vertical = 30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)
            ) {
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Packaging type",
                    subTitle = packageMyEntity.packagingType
                )
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Quantity",
                    subTitle = packageMyEntity.quantity.toString()
                )
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Shop Location",
                    subTitle = packageMyEntity.location
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            CirculoChip(
                chipType = if (chipString == ChipType.IN_PROGRESS.toString()) ChipType.IN_PROGRESS else ChipType.COMPLETED
            )
        }
    }
}

@Composable
fun CirculoListCardWithButton(
    packageListCardWithMethodEntity: PackageListCardWithMethodEntity,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale5,
                ambientColor = CirculerTheme.colors.grayScale5
            )
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 15.dp, vertical = 30.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, alignment = Alignment.CenterVertically)
        ) {
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Quantity",
                subTitle = packageListCardWithMethodEntity.quantity.toString()
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Shop Location",
                subTitle = packageListCardWithMethodEntity.location
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Method",
                subTitle = packageListCardWithMethodEntity.method
            )

            //todo: packageListCardWithMethodEntity.status
            CirculoButton(
                text = "accept",
                textStyle = CirculerTheme.typography.body4R12,
                verticalPadding = 10.dp,
                onClick = onButtonClick
            )
        }
    }
}

@Preview
@Composable
private fun CirculoListCardPreview() {
    CirculerTheme {
        Column(
            modifier = Modifier
                .background(CirculerTheme.colors.green4),
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)

        ) {
            CirculoListCard(
                listCardEntity = PackageListCardEntity(
                    distance = "16m",
                    type = "plastic",
                    id = 1,
                    location = "15m",
                    quantity = 3
                )
            )
        }
    }
}
