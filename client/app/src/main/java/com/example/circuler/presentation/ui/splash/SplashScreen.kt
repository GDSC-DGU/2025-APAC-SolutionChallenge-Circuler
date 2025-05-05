package com.example.circuler.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circuler.R
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .background(CirculerTheme.colors.yellow1)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_circulo_logo_large),
            contentDescription = null,
            modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.636).dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}
