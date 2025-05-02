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
import com.example.circuler.presentation.ui.history.navigation.historyNavGraph
import com.example.circuler.presentation.ui.home.navigation.homeNavGraph

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
        homeNavGraph(
            navigateToAddPackaging = navigator::navigateToAddPackaging,
            navigateToRequestedPackages = { navigator.navigate(MainTabType.REQUEST_PACKAGE) },
            navigateToReadyToGoPackages = navigator::navigateToHistory // todo: 올바른 경로로 수정
        )
        historyNavGraph()
        addNavGraph()
    }
}
