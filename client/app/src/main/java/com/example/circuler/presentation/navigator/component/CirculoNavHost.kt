package com.example.circuler.presentation.navigator.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.circuler.presentation.navigator.MainNavigator
import com.example.circuler.presentation.type.MainTabType
import com.example.circuler.presentation.ui.add.navigation.addNavGraph
import com.example.circuler.presentation.ui.delivery.navigation.deliveryNavGraph
import com.example.circuler.presentation.ui.history.navigation.historyNavGraph
import com.example.circuler.presentation.ui.home.navigation.homeNavGraph
import com.example.circuler.presentation.ui.login.navigation.loginNavGraph
import com.example.circuler.presentation.ui.request.navigation.requestGraph
import com.example.circuler.presentation.ui.splash.navigation.splashNavGraph
import com.example.circuler.presentation.ui.submit.navigation.submitNavGraph

@Composable
fun CirculoNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        splashNavGraph()
        loginNavGraph(
            navigateToHome = { navigator.navigate(tab = MainTabType.HOME) }
        )
        homeNavGraph(
            navigateToAddPackaging = navigator::navigateToAddPackaging,
            navigateToRequestedPackages = navigator::navigateToRequestPackage,
            navigateToReadyToGoPackages = navigator::navigateToReadyToGoPackages
        )
        historyNavGraph()
        addNavGraph(
            navigateUp = navigator::popBackStackIfNotHome,
        )
        deliveryNavGraph(
            navigateUp = navigator::popBackStackIfNotHome,
        )
        requestGraph(
            navigateUp = navigator::popBackStackIfNotHome,
        )
        submitNavGraph(
            navigateUp = navigator::popBackStackIfNotHome,
        )
    }
}
