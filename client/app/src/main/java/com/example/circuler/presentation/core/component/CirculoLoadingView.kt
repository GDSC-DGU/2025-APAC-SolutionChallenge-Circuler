package com.example.circuler.presentation.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.circuler.R
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun CirculoLoadingView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .align(Alignment.Center)
                .size(156.dp),
            iterations = Int.MAX_VALUE
        )
    }
}

@Preview
@Composable
fun CirculoLoadingViewPreview() {
    CirculerTheme {
        CirculoLoadingView()
    }
}