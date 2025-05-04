package com.example.circuler.presentation.ui.request

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.domain.entity.ListCardEntity
import com.example.circuler.presentation.core.component.CirculoListCard
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.roundedBackgroundWithBorder
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun RequestRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is RequestSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                }
            }
    }

    RequestScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RequestScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<String>,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    //todo: dummy delete
    val dummyList = listOf(
        ListCardEntity(
            distance = "2.5km",
            id = "101",
            location = "서울시 마포구",
            quantity = "10",
            type = "플라스틱"
        ),
        ListCardEntity(
            distance = "1.2km",
            id = "102",
            location = "서울시 강남구",
            quantity = "5",
            type = "종이"
        ),
        ListCardEntity(
            distance = "3.0km",
            id = "103",
            location = "서울시 송파구",
            quantity = "7",
            type = "유리"
        ),
        ListCardEntity(
            distance = "3.0km",
            id = "103",
            location = "서울시 송파구",
            quantity = "7",
            type = "유리"
        ),
        ListCardEntity(
            distance = "3.0km",
            id = "103",
            location = "서울시 송파구",
            quantity = "7",
            type = "유리"
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .statusBarsPadding()
            .navigationBarsPadding(),
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
                title = "Requested Package List",
            )
        }

        item {
            //todo: 버튼 디자인
            Text(
                modifier = Modifier
                    .padding(
                        all = 20.dp
                    )
                    .roundedBackgroundWithBorder(
                        cornerRadius = 20.dp,
                        backgroundColor = CirculerTheme.colors.grayScale1,
                        borderColor = CirculerTheme.colors.grayScale12,
                        borderWidth = 1.dp
                    )
                    .padding(
                        horizontal = 9.dp, vertical = 8.dp
                    ),
                text = "Packaging Type",
                textAlign = TextAlign.Start
            )
        }

        itemsIndexed(
            items = dummyList
            //todo: 주석 삭제
            //state.results
        ) { index, item ->
            CirculoListCard(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 4.dp),
                listCardEntity = ListCardEntity(
                    id = item.id,
                    location = item.location,
                    quantity = item.quantity,
                    distance = item.distance,
                    type = item.type
                )
            )
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
            state = UiState.Loading
        )
    }
}
