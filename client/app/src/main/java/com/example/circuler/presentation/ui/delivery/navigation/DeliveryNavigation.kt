package com.example.circuler.presentation.ui.delivery.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.delivery.DeliveryRoute

fun NavController.navigateToDeliveryPackage(navOptions: NavOptions? = null) {
    navigate(
        route = Route.DeliveryPackage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.deliveryNavGraph(
    navigateUp: () -> Unit
) {
    composable<Route.DeliveryPackage> {
        DeliveryRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp
        )
    }
}
