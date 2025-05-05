package com.example.circuler.presentation.ui.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.splash.SplashScreen

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    navigate(
        route = Route.Splash,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.splashNavGraph(
) {
    composable<Route.Splash> {
        SplashScreen()
    }
}