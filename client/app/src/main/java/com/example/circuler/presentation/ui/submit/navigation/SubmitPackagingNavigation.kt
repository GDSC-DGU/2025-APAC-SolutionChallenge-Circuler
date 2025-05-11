package com.example.circuler.presentation.ui.submit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.submit.SubmitPackagingRoute

fun NavController.navigateToSubmitPackaging(requestId: Int, navOptions: NavOptions? = null) {
    navigate(
        route = Route.SubmittedPackage(
            requestId = requestId
        ),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.submitNavGraph(
    navigateUp: () -> Unit
) {
    composable<Route.SubmittedPackage> { backStackEntry ->
        val requestId = backStackEntry.toRoute<Route.EnterPackage>().requestId
        SubmitPackagingRoute(
            paddingValues = PaddingValues(),
            requestId = requestId,
            navigateUp = navigateUp
        )
    }
}
