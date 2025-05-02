package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.R
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoTopBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CirculerTheme.colors.grayScale1,
    leadingIcon: @Composable () -> Unit = {},
    title: String? = null,
    titleStyle: TextStyle = CirculerTheme.typography.heading5Sb18,
    titleColor: Color = CirculerTheme.colors.grayScale12,
    trailingIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .height(48.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            leadingIcon()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title.orEmpty(),
                style = titleStyle,
                color = titleColor
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            trailingIcon()
        }
    }
}

@Preview
@Composable
private fun RoomieTopBarPreview() {
    CirculerTheme {
        Column(
            modifier = Modifier
                .background(color = CirculerTheme.colors.green2),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            //leading, title 있음
            CirculoTopBar(
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(all = 10.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                        contentDescription = "백버튼"
                    )
                },
                title = "제목"
            )

            //leading 있음
            CirculoTopBar(
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(all = 10.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                        contentDescription = "백버튼"
                    )
                }
            )

            //배경 투명색
            CirculoTopBar(
                backgroundColor = Color.Transparent,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(all = 10.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                        contentDescription = "백버튼"
                    )
                },
            )

            //title 있음
            CirculoTopBar(
                title = "제목"
            )

            CirculoTopBar(
                modifier = Modifier.statusBarsPadding(),
                backgroundColor = Color.Transparent,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(all = 10.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.img_circulo_logo_small),
                        contentDescription = stringResource(R.string.app_name)
                    )
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(all = 10.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_point),
                            contentDescription = null
                        )
                        Text(
                            text = "point"
                        )
                        Icon(
                            modifier = Modifier
                                .padding(all = 10.dp),
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}