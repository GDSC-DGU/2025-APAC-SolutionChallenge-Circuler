package com.example.circuler.presentation.ui.delivery

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
import com.example.circuler.domain.entity.DeliveryEntity
import com.example.circuler.presentation.core.component.CirculoDeliveryListCard
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun DeliveryRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToMap: () -> Unit,
    viewModel: DeliveryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val counter by remember { mutableIntStateOf(0) }

    val currentCounter by rememberUpdatedState(counter)

    LaunchedEffect(currentCounter) {
        viewModel.getDeliveryPending()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is DeliverySideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    is DeliverySideEffect.NavigateToMap -> navigateToMap()
                }
            }
    }

    DeliveryScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToMap = viewModel::navigateToMap,
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeliveryScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToMap: () -> Unit,
    state: UiState<List<DeliveryEntity>>,
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
            UiState.Failure -> {
            }

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
                        title = "Ready-to-Go Package List"
                    )
                }

                itemsIndexed(
                    items = state.data
                ) { index, item ->
                    CirculoDeliveryListCard(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
                        deliveryEntity = DeliveryEntity(
                            deliveryId = item.deliveryId,
                            packagingType = item.packagingType,
                            requestLocation = item.requestLocation,
                            storemanName = item.storemanName,
                            submissionLocation = item.submissionLocation,
                            submissionQuantity = item.submissionQuantity,
                            userName = item.userName
                        ),
                        onClick = {
                            navigateToMap()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DeliveryPreview() {
    CirculerTheme {
        DeliveryScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToMap = {},
            state = UiState.Loading
        )
    }
}
