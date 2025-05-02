package com.example.circuler.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.circuler.R
import com.example.circuler.presentation.core.component.CirculoTopBar
import com.example.circuler.presentation.core.extension.noRippleClickable
import com.example.circuler.presentation.core.extension.showToast
import com.example.circuler.presentation.core.util.UiState
import com.example.circuler.ui.theme.CirculerTheme

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> context.showToast(message = sideEffect.message)
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        state = state.uiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    state: UiState<String>,
    modifier: Modifier = Modifier
) {
    val screenWeigth = LocalConfiguration.current.screenWidthDp
    val height = (screenWeigth * 0.5).dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CirculerTheme.colors.grayScale1)
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        stickyHeader {
            CirculoTopBar(
                modifier = Modifier.statusBarsPadding(),
                backgroundColor = Color.Transparent,
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



    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CirculerTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            navigateUp = {},
            state = UiState.Loading
        )
    }
}
