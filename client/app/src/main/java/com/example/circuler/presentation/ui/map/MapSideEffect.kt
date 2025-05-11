package com.example.circuler.presentation.ui.map

sealed class MapSideEffect {
    data class ShowToast(val message: String) : MapSideEffect()
    data object NavigateToHome : MapSideEffect()
}
