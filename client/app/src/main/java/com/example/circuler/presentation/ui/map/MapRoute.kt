package com.example.circuler.presentation.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: MapViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MapSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    is MapSideEffect.NavigateToHome -> navigateToHome()
                }
            }
    }

    MapScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        onButtonClick = {
            viewModel.postDeliveryRequest(deliveryId = 2)
        },
        state = state.uiState
    )
}

@Composable
fun MapScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    onButtonClick: () -> Unit,
    state: UiState<String>,
    modifier: Modifier = Modifier
) {
    val dgu = LatLng(37.558391, 127.000227)
    val shop = LatLng(37.557131, 127.004303)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(dgu, 15f)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = dgu),
                title = "Dongguk University",
                snippet = "You are here"
            )

            Marker(
                state = MarkerState(position = shop),
                title = "Bloom & Bean",
                snippet = "Marker in Shop"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            CirculoButton(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 40.dp),
                text = "delivery request",
                textStyle = CirculerTheme.typography.heading4B18,
                onClick = onButtonClick
            )
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    CirculerTheme {
        MapScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            onButtonClick = {},
            state = UiState.Loading
        )
    }
}
