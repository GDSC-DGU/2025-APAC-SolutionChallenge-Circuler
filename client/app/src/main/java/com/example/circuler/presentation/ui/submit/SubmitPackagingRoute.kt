package com.example.circuler.presentation.ui.submit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.circuler.domain.entity.ListCardWithMethodEntity
import com.example.circuler.presentation.core.component.CirculoListCardWithButton
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun SubmitPackagingRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: SubmitPackagingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

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
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubmitPackagingScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<String>,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    // todo: dummy delete
    val dummyList = listOf(
        ListCardWithMethodEntity(
            id = "1",
            location = "서울",
            method = "재활용",
            quantity = "5"
        ),
        ListCardWithMethodEntity(
            id = "1",
            location = "서울",
            method = "재활용",
            quantity = "5"
        ),
        ListCardWithMethodEntity(
            id = "1",
            location = "서울",
            method = "재활용",
            quantity = "5"
        ),
        ListCardWithMethodEntity(
            id = "2",
            location = "부산",
            method = "소각",
            quantity = "3"
        ),
        ListCardWithMethodEntity(
            id = "3",
            location = "인천",
            method = "매립",
            quantity = "7"
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
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
            items = dummyList
            // todo: 주석 삭제
            // state.results
        ) { index, item ->
            CirculoListCardWithButton(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
                listCardWithMethodEntity = ListCardWithMethodEntity(
                    id = item.id,
                    location = item.location,
                    method = item.method,
                    quantity = item.quantity
                )
            )
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
            state = UiState.Loading
        )
    }
}
