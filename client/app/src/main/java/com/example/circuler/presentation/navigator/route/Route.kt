package com.example.circuler.presentation.navigator.route

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object AddPackage : Route

    @Serializable
    data object EntirePackageRequest : Route

    @Serializable
    data class EnterPackage(
        val requestId: Int
    ) : Route

    @Serializable
    data class ConfirmPackage(
        val requestId: Int
    ) : Route

    @Serializable
    data object UploadPhoto : Route

    @Serializable
    data object DeliveryPackage : Route

    @Serializable
    data object SubmittedPackage : Route

    @Serializable
    data object Map : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object RequestPackage : MainTabRoute
}
