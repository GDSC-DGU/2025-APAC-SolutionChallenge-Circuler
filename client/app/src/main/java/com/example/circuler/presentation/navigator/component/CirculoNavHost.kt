package com.example.circuler.presentation.navigator.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.circuler.presentation.navigator.MainNavigator
import com.example.circuler.presentation.navigator.route.MainTabRoute
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
        startDestination = MainTabRoute.Home
    ) {
        homeNavGraph()
        historyNavGraph()
    }
}