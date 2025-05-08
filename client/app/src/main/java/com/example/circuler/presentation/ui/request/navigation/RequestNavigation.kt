package com.example.circuler.presentation.ui.request.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.request.RequestRoute

fun NavController.navigateToRequestPackage(navOptions: NavOptions? = null) {
    navigate(
        route = Route.EntirePackageRequest,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.requestGraph(
    navigateUp: () -> Unit,
    navigateToEnterPackaging: () -> Unit
) {
    composable<Route.EntirePackageRequest> {
        RequestRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp,
            navigateToEnterPackaging = navigateToEnterPackaging
        )
    }
}
