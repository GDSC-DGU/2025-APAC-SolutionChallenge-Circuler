package com.example.circuler.presentation.ui.enter

sealed class EnterPackagingSideEffect {
    data class ShowToast(val message: String) : EnterPackagingSideEffect()
    data class NavigateToConfirmPackage(
        val requestId: Int
    ) : EnterPackagingSideEffect()
}
