package com.example.circuler.presentation.ui.map.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.map.MapRoute

fun NavController.navigateToMap(navOptions: NavOptions? = null) {
    navigate(
        route = Route.Map,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.mapNavGraph(
    navigateToHome: () -> Unit
) {
    composable<Route.Map> {
        MapRoute(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToHome = navigateToHome
        )
    }
}
