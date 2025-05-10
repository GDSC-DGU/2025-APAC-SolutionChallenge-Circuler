package com.example.circuler.presentation.ui.request

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
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.presentation.core.component.CirculoListCard
import com.example.circuler.presentation.core.component.CirculoLoadingView
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.EmptyUiState
import com.example.circuler.presentation.ui.request.component.RequestSortButton
import com.example.circuler.ui.theme.CirculerTheme

// todo: navigateToEnterPackaging id 값 물고가기
@Composable
fun RequestRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToEnterPackaging: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val counter by remember { mutableIntStateOf(0) }

    val currentCounter by rememberUpdatedState(counter)

    LaunchedEffect(currentCounter) {
        viewModel.getPackages()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is RequestSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                    RequestSideEffect.NavigateToEnterPackaging -> navigateToEnterPackaging()
                }
            }
    }

    RequestScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        navigateToEnterPackaging = viewModel::navigateToEnterPackaging,
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RequestScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateToEnterPackaging: () -> Unit,
    state: EmptyUiState<List<PackageListCardEntity>>,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (state) {
            is EmptyUiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .height((LocalConfiguration.current.screenHeightDp).dp),
                    ) {
                        CirculoLoadingView()
                    }
                }
            }

            is EmptyUiState.Success -> {
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
                        title = "Requested Package List"
                    )
                }

                item {
                    RequestSortButton(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                    )
                }

                itemsIndexed(
                    items = state.data
                ) { index, item ->
                    CirculoListCard(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
                        listCardEntity = PackageListCardEntity(
                            id = item.id,
                            location = item.location,
                            quantity = item.quantity,
                            distance = item.distance,
                            type = item.type
                        ),
                        onClick = {
                            navigateToEnterPackaging()
                        }
                    )
                }

            }

            is EmptyUiState.Failure -> {

            }

            is EmptyUiState.Empty -> {

            }
        }
    }
}

@Preview
@Composable
fun RequestScreenPreview() {
    CirculerTheme {
        RequestScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToEnterPackaging = {},
            state = EmptyUiState.Loading
        )
    }
}
