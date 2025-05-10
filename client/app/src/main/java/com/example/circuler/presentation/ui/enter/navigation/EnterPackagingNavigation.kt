package com.example.circuler.presentation.ui.enter.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.enter.EnterPackagingRoute

fun NavController.navigateToEnterPackaging(requestId: Int, navOptions: NavOptions? = null) {
    navigate(
        route = Route.EnterPackage(
            requestId = requestId
        ),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.enterNavGraph(
    navigateUp: () -> Unit,
    navigateToConfirmPackage: (Int) -> Unit
) {
    composable<Route.EnterPackage> { backStackEntry ->
        val requestId = backStackEntry.toRoute<Route.EnterPackage>().requestId
        EnterPackagingRoute(
            paddingValues = PaddingValues(),
            requestId = requestId,
            navigateUp = navigateUp,
            navigateToConfirmPackage = navigateToConfirmPackage
        )
    }
}
