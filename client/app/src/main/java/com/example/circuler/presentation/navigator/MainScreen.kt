package com.example.circuler.presentation.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.circuler.presentation.navigator.component.CirculoNavHost
import com.example.circuler.presentation.navigator.component.MainBottomBar
import com.example.circuler.presentation.type.MainTabType
import com.example.circuler.ui.theme.CirculerTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    MainScreenContent(
        navigator = navigator,
        snackBarHostState = snackBarHostState
    )
}

@Composable
private fun MainScreenContent(
    navigator: MainNavigator,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            CirculoNavHost(
                navigator = navigator,
                padding = padding
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .background(color = CirculerTheme.colors.grayScale1)
                    .navigationBarsPadding(),
                isVisible = navigator.showBottomBar(),
                tabs = MainTabType.entries.toImmutableList(),
                currentTabSelected = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CirculerTheme {
        MainScreen(navigator = rememberMainNavigator())
    }
}
