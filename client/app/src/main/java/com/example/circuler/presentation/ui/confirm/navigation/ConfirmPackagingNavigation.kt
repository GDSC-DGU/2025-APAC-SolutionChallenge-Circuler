package com.example.circuler.presentation.ui.confirm.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.confirm.ConfirmPackagingRoute

fun NavController.navigateToConfirmPackaging(navOptions: NavOptions? = null) {
    navigate(
        route = Route.ConfirmPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.confirmNavGraph(
    navigateUp: () -> Unit,
    navigateToUploadPackage: () -> Unit
) {
    composable<Route.ConfirmPackage> {
        ConfirmPackagingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp,
            navigateToUploadPackage = navigateToUploadPackage
        )
    }
}
