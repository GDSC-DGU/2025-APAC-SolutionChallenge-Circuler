package com.example.circuler.presentation.ui.confirm

sealed class ConfirmPackagingSideEffect {
    data class ShowToast(val message: String) : ConfirmPackagingSideEffect()
    data object NavigateToUploadPackage : ConfirmPackagingSideEffect()
}
