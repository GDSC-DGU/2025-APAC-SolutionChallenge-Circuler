package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoTextWithIcon(
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
private fun CirculoTextPreview() {
    CirculerTheme {
        Column(
            modifier = Modifier
                .background(CirculerTheme.colors.green4),
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)

        ) {
            CirculoTextWithIcon(
                icon = Icons.Outlined.Menu,
                title = "Quantity",
                subTitle = "5"
            )
        }
    }
}
