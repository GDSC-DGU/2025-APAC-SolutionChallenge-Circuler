package com.example.circuler.presentation.ui.upload

sealed class UploadPackagingSideEffect {
    data class ShowToast(val message: String) : UploadPackagingSideEffect()
    data object NavigateToHome : UploadPackagingSideEffect()
}
