package com.example.circuler.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.type.HomeNavigateButtonType
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun HomeNavigateButton(
    homeNavigateButtonType: HomeNavigateButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 20.dp,
                backgroundColor = CirculerTheme.colors.green4,
                borderWidth = 1.dp,
                borderColor = CirculerTheme.colors.green1
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .height((LocalConfiguration.current.screenHeightDp * 0.251).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(homeNavigateButtonType.buttonIcon),
            tint = CirculerTheme.colors.green2,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = stringResource(homeNavigateButtonType.buttonTitle),
            style = CirculerTheme.typography.title1R16,
            color = CirculerTheme.colors.grayScale12
        )
    }
}

@Preview
@Composable
private fun HomeMainButtonPreview() {
    CirculerTheme {
        Row(
            modifier = Modifier
                .background(color = CirculerTheme.colors.grayScale1),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            HomeNavigateButton(
                modifier = Modifier.weight(1f),
                homeNavigateButtonType = HomeNavigateButtonType.REQUEST,
                onClick = {}
            )
            HomeNavigateButton(
                modifier = Modifier.weight(1f),
                homeNavigateButtonType = HomeNavigateButtonType.DELIVERY,
                onClick = {}
            )
        }
    }
}
