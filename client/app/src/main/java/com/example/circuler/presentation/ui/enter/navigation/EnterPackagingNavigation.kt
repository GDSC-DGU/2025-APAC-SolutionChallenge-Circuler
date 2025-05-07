package com.example.circuler.presentation.ui.enter.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.enter.EnterPackagingRoute

fun NavController.navigateToEnterPackaging(navOptions: NavOptions? = null) {
    navigate(
        route = Route.EnterPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.enterNavGraph(
    navigateUp: () -> Unit,
    navigateToConfirmPackage: () -> Unit
) {
    composable<Route.EnterPackage> {
        EnterPackagingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp,
            navigateToConfirmPackage = navigateToConfirmPackage
        )
    }
}
