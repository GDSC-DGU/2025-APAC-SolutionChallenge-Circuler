package com.example.circuler.presentation.ui.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun UploadPackagingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: UploadPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is UploadPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    UploadPackagingSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    UploadPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToHome = viewModel::navigateToHome,
        state = state.uiState
    )
}

@Composable
fun UploadPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<String>,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // todo: UiState.Success
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CirculerTheme.colors.grayScale1)
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_upload_success),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            CirculoButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                text = "accept",
                onClick = {
                    navigateToHome()
                }
            )
        }

        // todo: UiState.Loading
//        Box(
//            modifier = Modifier
//                .height((LocalConfiguration.current.screenHeightDp).dp),
//        ) {
//            CirculoLoadingView()
//        }
    }
}

@Preview
@Composable
fun UploadPackagingScreenPreview() {
    CirculerTheme {
        UploadPackagingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToHome = {},
            state = UiState.Loading
        )
    }
}
