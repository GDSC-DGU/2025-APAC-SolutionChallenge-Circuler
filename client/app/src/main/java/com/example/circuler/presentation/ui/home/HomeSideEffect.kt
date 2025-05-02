package com.example.circuler.presentation.ui.home

sealed class HomeSideEffect {
    data class ShowToast(val message: String) : HomeSideEffect()
}
