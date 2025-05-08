package com.example.circuler.presentation.ui.add

sealed class AddPackagingSideEffect {
    data class ShowToast(val message: String) : AddPackagingSideEffect()
    data object NavigateToHome : AddPackagingSideEffect()
}
