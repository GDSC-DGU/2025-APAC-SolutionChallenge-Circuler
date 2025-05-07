package com.example.circuler.presentation.ui.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.circuler.domain.entity.HistoryDataEntity
import com.example.circuler.domain.entity.ListCardWithMethodEntity
import com.example.circuler.presentation.core.component.CirculoListCardWithMethod
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.showToast
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
    state: HistoryDataEntity,
    navigateToSubmit: () -> Unit,
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
            .padding(bottom = paddingValues.calculateBottomPadding())
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            items = dummyList
            // todo: 주석 삭제
            // state.results
        ) { index, item ->
            val bottomPadding = if (index == dummyList.lastIndex) 70.dp else 4.dp

            CirculoListCardWithMethod(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = bottomPadding),
                listCardWithMethodEntity = ListCardWithMethodEntity(
                    id = item.id,
                    location = item.location,
                    method = item.method,
                    quantity = item.quantity
                ),
                chipType = ChipType.IN_PROGRESS,
                onClick = {
                    // todo: chiptype이 matching일때만 (entity 확인)
                    navigateToSubmit()
                }
            )
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
            state = HistoryDataEntity(
                code = "",
                isSuccess = "",
                results = listOf(
                    ListCardWithMethodEntity(
                        id = "1",
                        location = "12",
                        method = "plastic",
                        quantity = "6"
                    )
                )
            ),
            navigateToSubmit = {}
        )
    }
}
