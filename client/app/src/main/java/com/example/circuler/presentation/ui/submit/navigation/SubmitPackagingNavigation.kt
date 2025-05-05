package com.example.circuler.presentation.ui.submit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.submit.SubmitPackagingRoute

fun NavController.navigateToSubmitPackaging(navOptions: NavOptions? = null) {
    navigate(
        route = Route.SubmittedPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.submitNavGraph(
    navigateUp: () -> Unit,
) {
    composable<Route.SubmittedPackage> {
        SubmitPackagingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp
        )
    }
}
