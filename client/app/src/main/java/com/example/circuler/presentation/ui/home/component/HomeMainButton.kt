package com.example.circuler.presentation.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.R
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun HomeMainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height((LocalConfiguration.current.screenHeightDp * 0.228).dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(id = R.drawable.img_add_packaging),
            contentDescription = null,
            alpha = 0.3f,
            contentScale = ContentScale.Crop

        )

        Column(
            modifier = Modifier
                .matchParentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle),
                contentDescription = null,
                tint = CirculerTheme.colors.green1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Add Packaging",
                style = CirculerTheme.typography.body4R12,
                color = CirculerTheme.colors.grayScale12
            )
        }
    }
}

@Preview
@Composable
private fun HomeMainButtonPreview() {
    CirculerTheme {
        Row(
            modifier = Modifier
                .background(color = CirculerTheme.colors.grayScale1),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomeMainButton(
                onClick = {}
            )
        }
    }
}
