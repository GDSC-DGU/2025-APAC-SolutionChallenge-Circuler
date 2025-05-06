package com.example.circuler.presentation.ui.submit

sealed class SubmitPackagingSideEffect {
    data class ShowToast(val message: String) : SubmitPackagingSideEffect()
}
