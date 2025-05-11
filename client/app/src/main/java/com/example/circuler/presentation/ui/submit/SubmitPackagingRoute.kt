package com.example.circuler.presentation.ui.submit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.presentation.core.component.CirculoListCardWithButton
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun SubmitPackagingRoute(
    paddingValues: PaddingValues,
    requestId: Int,
    navigateUp: () -> Unit,
    viewModel: SubmitPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val counter by remember { mutableIntStateOf(0) }

    val currentCounter by rememberUpdatedState(counter)

    LaunchedEffect(currentCounter) {
        viewModel.getSubmittedData(requestId = requestId)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SubmitPackagingSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                }
            }
    }

    SubmitPackagingScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        state = state.uiState,
        onButtonClick = {
            viewModel.getPackageAccept(requestId = requestId, submissionId = 1) // todo: submissionId 전달방법
            viewModel.postPackagingDelivery(requestId = requestId)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubmitPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<List<PackageListCardWithMethodEntity>>,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (state) {
            UiState.Failure -> {}
            UiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .height((LocalConfiguration.current.screenHeightDp).dp)
                    ) {
                        CirculoLoadingView()
                    }
                }
            }

            is UiState.Success -> {
                stickyHeader {
                    CirculoTopBar(
                        leadingIcon = {
                            Icon(
                                modifier = Modifier
                                    .noRippleClickable { navigateUp() }
                                    .padding(all = 10.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                                contentDescription = "back"
                            )
                        },
                        title = "Submitted Package List"
                    )
                }

                itemsIndexed(
                    items = state.data
                ) { index, item ->
                    CirculoListCardWithButton(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
                        packageListCardWithMethodEntity = PackageListCardWithMethodEntity(
                            id = item.id,
                            location = item.location,
                            method = item.method,
                            quantity = item.quantity,
                            status = item.status
                        ),
                        onButtonClick = onButtonClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SubmitPackagingScreenPreview() {
    CirculerTheme {
        SubmitPackagingScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            state = UiState.Loading,
            onButtonClick = {}
        )
    }
}
