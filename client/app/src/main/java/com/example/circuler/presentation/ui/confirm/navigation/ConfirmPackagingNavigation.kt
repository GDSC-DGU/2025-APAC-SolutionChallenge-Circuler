package com.example.circuler.presentation.ui.confirm.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.confirm.ConfirmPackagingRoute

fun NavController.navigateToConfirmPackaging(requestId: Int, navOptions: NavOptions? = null) {
    navigate(
        route = Route.ConfirmPackage(
            requestId = requestId
        ),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.confirmNavGraph(
    navigateUp: () -> Unit,
    navigateToUploadPackage: () -> Unit
) {
    composable<Route.ConfirmPackage> { backStackEntry ->
        val requestId = backStackEntry.toRoute<Route.EnterPackage>().requestId
        ConfirmPackagingRoute(
            paddingValues = PaddingValues(),
            requestId = requestId,
            navigateUp = navigateUp,
            navigateToUploadPackage = navigateToUploadPackage
        )
    }
}
