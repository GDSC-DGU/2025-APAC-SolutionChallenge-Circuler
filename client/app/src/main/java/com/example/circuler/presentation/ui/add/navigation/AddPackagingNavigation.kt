package com.example.circuler.presentation.ui.add.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.add.AddPackagingRoute

fun NavController.navigateToAddPackaging(navOptions: NavOptions? = null) {
    navigate(
        route = Route.AddPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.addNavGraph(
    navigateUp: () -> Unit,
) {
    composable<Route.AddPackage> {
        AddPackagingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp
        )
    }
}
