package com.example.circuler.presentation.ui.home

sealed class HomeSideEffect {
    data class ShowToast(val message: String) : HomeSideEffect()
    data object NavigateToAddPackaging : HomeSideEffect()
    data object NavigateToRequestedPackages : HomeSideEffect()
    data object NavigateToReadyToGoPackages : HomeSideEffect()
    data object NavigateToMap : HomeSideEffect()
}
