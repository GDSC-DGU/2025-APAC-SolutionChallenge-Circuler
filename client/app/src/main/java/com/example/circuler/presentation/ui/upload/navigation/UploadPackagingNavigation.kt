package com.example.circuler.presentation.ui.upload.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.ui.upload.UploadPackagingRoute

fun NavController.navigateToUploadPackaging(navOptions: NavOptions? = null) {
    navigate(
        route = Route.UploadPhoto,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.uploadNavGraph(
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable<Route.UploadPhoto> {
        UploadPackagingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp,
            navigateToHome = navigateToHome
        )
    }
}
