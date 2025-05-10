package com.example.circuler.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.circuler.presentation.navigator.route.MainTabRoute
import com.example.circuler.presentation.navigator.route.Route
import com.example.circuler.presentation.type.MainTabType
import com.example.circuler.presentation.ui.add.navigation.navigateToAddPackaging
import com.example.circuler.presentation.ui.confirm.navigation.navigateToConfirmPackaging
import com.example.circuler.presentation.ui.delivery.navigation.navigateToDeliveryPackage
import com.example.circuler.presentation.ui.enter.navigation.navigateToEnterPackaging
import com.example.circuler.presentation.ui.history.navigation.navigateToHistory
import com.example.circuler.presentation.ui.home.navigation.navigateToHome
import com.example.circuler.presentation.ui.request.navigation.navigateToRequestPackage
import com.example.circuler.presentation.ui.submit.navigation.navigateToSubmitPackaging
import com.example.circuler.presentation.ui.upload.navigation.navigateToUploadPackaging

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.Login

    val currentTab: MainTabType?
        @Composable get() = MainTabType.find { tab ->
            currentDestination?.route?.startsWith(tab::class.qualifiedName ?: "") == true
        }

    fun navigate(tab: MainTabType) {
        val navOptions = navOptions {
            popUpTo<MainTabRoute.Home> {
                inclusive = false
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

    fun navigateToHome(
        navOptions: NavOptions
    ) {
        navController.navigate(MainTabRoute.Home, navOptions)
    }

    fun navigateToHistory() {
        navController.navigate(MainTabRoute.RequestPackage)
    }

    fun navigateToAddPackaging() {
        navController.navigateToAddPackaging()
    }

    fun navigateToReadyToGoPackages() {
        navController.navigateToDeliveryPackage()
    }

    fun navigateToRequestPackage() {
        navController.navigateToRequestPackage()
    }

    fun navigateToEnterPackaging(requestId: Int) {
        navController.navigateToEnterPackaging(requestId = requestId)
    }

    fun navigateToConfirmPackaging(requestId: Int) {
        navController.navigateToConfirmPackaging(requestId = requestId)
    }

    fun navigateToSubmitPackaging() {
        navController.navigateToSubmitPackaging()
    }

    fun navigateToUploadPackaging() {
        navController.navigateToUploadPackaging()
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
