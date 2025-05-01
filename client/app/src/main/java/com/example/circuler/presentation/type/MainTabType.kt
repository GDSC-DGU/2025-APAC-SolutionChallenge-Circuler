package com.example.circuler.presentation.type

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.circuler.R
import com.example.circuler.presentation.navigator.route.MainTabRoute
import com.example.circuler.presentation.navigator.route.Route

enum class MainTabType(
    val tabIcon: ImageVector,
    @StringRes val tabTitle: Int,
    val route: MainTabRoute
) {
    HOME(
        tabIcon = Icons.Outlined.Home,
        tabTitle = R.string.home,
        route = MainTabRoute.Home
    ),
    REQUEST_PACKAGE(
        tabIcon = Icons.Outlined.Menu,
        tabTitle = R.string.history,
        route = MainTabRoute.RequestPackage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTabType? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
