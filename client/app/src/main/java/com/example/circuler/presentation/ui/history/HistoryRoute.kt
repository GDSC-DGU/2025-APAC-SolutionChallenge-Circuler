package com.example.circuler.presentation.ui.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.domain.entity.PackageMyEntity
import com.example.circuler.presentation.core.component.CirculoListCardWithMethod
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.presentation.type.ChipType
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun HistoryRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToSubmit: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val counter by remember { mutableIntStateOf(0) }

    val currentCounter by rememberUpdatedState(counter)

    LaunchedEffect(currentCounter) {
        viewModel.getPackagingRequestMy()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HistorySideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    HistorySideEffect.NavigateToSubmit -> navigateToSubmit()
                }
            }
    }

    HistoryScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToSubmit = viewModel::navigateToSubmit,
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<List<PackageMyEntity>>,
    navigateToSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .padding(bottom = paddingValues.calculateBottomPadding())
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            UiState.Failure -> {}
            UiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .height((LocalConfiguration.current.screenHeightDp).dp),
                    ) {
                        CirculoLoadingView()
                    }
                }
            }

            is UiState.Success -> {
                stickyHeader {
                    CirculoTopBar(
                        modifier = Modifier.statusBarsPadding(),
                        backgroundColor = CirculerTheme.colors.grayScale1,
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .width((LocalConfiguration.current.screenWidthDp * 0.164).dp),
                                painter = painterResource(R.drawable.img_circulo_logo_small),
                                contentDescription = stringResource(R.string.app_name),
                                contentScale = ContentScale.Crop
                            )
                        },
                        trailingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(all = 10.dp),
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_point),
                                    contentDescription = null
                                )
                                Text(
                                    text = "23456"
                                )
                                Spacer(
                                    modifier = Modifier
                                        .padding(start = 18.dp)
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

                itemsIndexed(
                    items = state.data
                ) { index, item ->
                    val bottomPadding = if (index == state.data.lastIndex) 70.dp else 4.dp

                    CirculoListCardWithMethod(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = bottomPadding),
                        packageMyEntity = PackageMyEntity(
                            createdAt = item.createdAt,
                            packagingType = item.packagingType,
                            requestId = item.requestId,
                            status = item.status,
                            location = item.location,
                            quantity = item.quantity
                        ),
                        chipString = item.status,
                        onClick = {
                            if (item.status == ChipType.IN_PROGRESS.toString()) {
                                navigateToSubmit()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    CirculerTheme {
        HistoryScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            state = UiState.Loading,
            navigateToSubmit = {}
        )
    }
}
