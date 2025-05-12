package com.example.circuler.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.MainTabRoute
import com.example.circuler.presentation.ui.home.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(
        route = MainTabRoute.Home,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    navigateToAddPackaging: () -> Unit,
    navigateToRequestedPackages: () -> Unit,
    navigateToReadyToGoPackages: () -> Unit,
    navigateToMap: () -> Unit
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToAddPackaging = navigateToAddPackaging,
            navigateToRequestedPackages = navigateToRequestedPackages,
            navigateToReadyToGoPackages = navigateToReadyToGoPackages,
            navigateToMap = navigateToMap
        )
    }
}
