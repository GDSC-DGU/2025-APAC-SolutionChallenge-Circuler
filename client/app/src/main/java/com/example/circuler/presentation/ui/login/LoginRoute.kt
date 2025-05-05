package com.example.circuler.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
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
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun LoginRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LoginSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    LoginSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    LoginScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToHome = viewModel::navigateToHome,
        state = state.uiState
    )
}

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    state: UiState<String>,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    Column(
        modifier = modifier
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

        Text(
            text = "google login",
            modifier = Modifier
                .noRippleClickable {
                    navigateToHome()
                }
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    CirculerTheme {
        LoginScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToHome = {},
            state = UiState.Loading
        )
    }
}
