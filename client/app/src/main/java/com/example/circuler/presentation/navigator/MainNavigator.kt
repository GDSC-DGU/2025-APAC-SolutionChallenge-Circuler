package com.example.circuler.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.circuler.presentation.navigator.route.MainTabRoute
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.type.MainTabType
import com.example.circuler.presentation.ui.history.navigation.navigateToHistory
import com.example.circuler.presentation.ui.home.navigation.navigateToHome

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTabRoute.Home

    val currentTab: MainTabType?
        @Composable get() = MainTabType.find { tab ->
            currentDestination?.route?.startsWith(tab::class.qualifiedName ?: "") == true
        }

    fun navigate(tab: MainTabType) {
        val navOptions = navOptions {
            popUpTo<MainTabRoute.Home> {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTabType.HOME -> navController.navigateToHome(navOptions)
            MainTabType.REQUEST_PACKAGE -> navController.navigateToHistory(navOptions)
        }
    }

    private fun navigateUp() {
        navController.navigateUp()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainTabRoute.Home>()) {
            navigateUp()
        }
    }

    fun navigateToHome() {
        navController.navigate(MainTabRoute.Home)
    }

    fun navigateToHistory() {
        navController.navigate(MainTabRoute.RequestPackage)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.qualifiedName

    @Composable
    fun showBottomBar() = MainTabType.contains {
        currentDestination?.route == it::class.qualifiedName
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
