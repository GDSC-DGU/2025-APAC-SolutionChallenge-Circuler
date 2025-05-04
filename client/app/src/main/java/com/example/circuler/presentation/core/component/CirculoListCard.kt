package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.domain.entity.ListCardEntity
import com.example.circuler.presentation.core.extension.customShadow
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.type.ChipType
import com.example.circuler.ui.theme.CirculerTheme

//todo: entity 수정가능성
@Composable
fun CirculoListCard(
    listCardEntity: ListCardEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1,
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale2,
                ambientColor = CirculerTheme.colors.grayScale2
            )
            .clickable {
                onClick()
            }
            .padding(start = 15.dp, top = 30.dp, bottom = 30.dp),
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
                subTitle = listCardEntity.quantity
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Shop Location",
                subTitle = listCardEntity.location
            )
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Distance",
                subTitle = listCardEntity.distance
            )
        }

    }
}

//todo: entity 수정가능성
@Composable
fun CirculoListCardWithMethod(
    listCardEntity: ListCardEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.grayScale1,
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .customShadow(
                spotColor = CirculerTheme.colors.grayScale2,
                ambientColor = CirculerTheme.colors.grayScale2
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 15.dp, vertical = 30.dp),
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
                    subTitle = listCardEntity.type
                )
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Quantity",
                    subTitle = listCardEntity.quantity
                )
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Shop Location",
                    subTitle = listCardEntity.location
                )
                CirculoTextWithIcon(
                    icon = Icons.Outlined.Menu,
                    title = "Distance",
                    subTitle = listCardEntity.distance
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            CirculoChip(
                chipType = ChipType.PENDING
            )
        }


    }
}

@Composable
private fun CirculoTextWithIcon(
    icon: ImageVector,
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier
                .size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = CirculerTheme.colors.grayScale8
        )
        Spacer(
            modifier = Modifier
                .size(2.dp)
        )
        Text(
            text = title,
            style = CirculerTheme.typography.body4R12,
            color = CirculerTheme.colors.grayScale12
        )
        Spacer(
            modifier = Modifier
                .size(20.dp)
        )
        Text(
            text = subTitle,
            style = CirculerTheme.typography.body4R12,
            color = CirculerTheme.colors.grayScale12
        )
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
                listCardEntity = ListCardEntity(
                    distance = "16m",
                    type = "plastic",
                    id = "1",
                    location = "15m",
                    quantity = "3"
                ),
            )

            CirculoListCardWithMethod(
                listCardEntity = ListCardEntity(
                    distance = "16m",
                    type = "plastic",
                    id = "1",
                    location = "15m",
                    quantity = "3"
                )
            )
        }
    }
}