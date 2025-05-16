package com.example.circuler.presentation.ui.request.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.R
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun RequestSortButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = CirculerTheme.colors.grayScale1,
                borderWidth = 1.dp,
                borderColor = CirculerTheme.colors.grayScale12
            )
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
            contentDescription = null,
            tint = CirculerTheme.colors.grayScale12
        )
        Text(
            text = "Ice Pack",
            style = CirculerTheme.typography.title3M16,
            color = CirculerTheme.colors.grayScale12
        )
    }
}

@Preview
@Composable
private fun RequestSortButtonPreview() {
    CirculerTheme {
        RequestSortButton()
    }
}
