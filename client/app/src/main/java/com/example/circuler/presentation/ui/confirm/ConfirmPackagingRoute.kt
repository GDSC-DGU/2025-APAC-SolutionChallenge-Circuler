package com.example.circuler.presentation.ui.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.presentation.core.component.CirculoButton
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.presentation.ui.confirm.component.CirculoTextGroup
import com.example.circuler.presentation.ui.confirm.component.ConfirmTitle
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun ConfirmPackagingRoute(
    paddingValues: PaddingValues,
    requestId: Int,
    navigateUp: () -> Unit,
    navigateToUploadPackage: () -> Unit,
    viewModel: ConfirmPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val counter by remember { mutableIntStateOf(0) }

    val currentCounter by rememberUpdatedState(counter)

    LaunchedEffect(currentCounter) {
        viewModel.getPackageDetail(requestId = requestId)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ConfirmPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    ConfirmPackagingSideEffect.NavigateToUploadPackage -> navigateToUploadPackage()
                }
            }
    }

    ConfirmPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToUploadPackage = viewModel::navigateToUploadPackage,
        state = state.uiState
    )
}

@Composable
fun ConfirmPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<PackageListCardEntity>,
    navigateToUploadPackage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (state) {
            UiState.Failure -> {}
            UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .height((LocalConfiguration.current.screenHeightDp).dp),
                ) {
                    CirculoLoadingView()
                }
            }
            is UiState.Success -> {

                CirculoTopBar(
                    leadingIcon = {
                        Icon(
                            modifier = Modifier
                                .noRippleClickable { navigateUp() }
                                .padding(all = 10.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                            contentDescription = "back"
                        )
                    }
                )

                ConfirmTitle()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
                ) {
                    CirculoTextGroup(
                        packageListCardEntity = state.data
                    )

                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )

                    CirculoButton(
                        text = "Yes, it’s correct",
                        onClick = {
                            navigateToUploadPackage()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ConfirmPackagingScreenPreview() {
    CirculerTheme {
        ConfirmPackagingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToUploadPackage = {},
            state = UiState.Loading
        )
    }
}
