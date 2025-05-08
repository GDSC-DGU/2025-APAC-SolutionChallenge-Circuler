package com.example.circuler.presentation.ui.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.login.LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(
        route = Route.Login,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.loginNavGraph(
    navigateToHome: () -> Unit
) {
    composable<Route.Login> {
        LoginRoute(
            paddingValues = PaddingValues(),
            navigateUp = {},
            navigateToHome = navigateToHome
        )
    }
}
