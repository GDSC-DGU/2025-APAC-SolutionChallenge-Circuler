package com.example.circuler.presentation.ui.history.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.MainTabRoute
import com.example.circuler.presentation.ui.history.HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions) {
    navigate(
        route = MainTabRoute.RequestPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.historyNavGraph() {
    composable<MainTabRoute.RequestPackage> {
        HistoryRoute(
            paddingValues = PaddingValues(),
            navigateUp = {}
        )
    }
}
