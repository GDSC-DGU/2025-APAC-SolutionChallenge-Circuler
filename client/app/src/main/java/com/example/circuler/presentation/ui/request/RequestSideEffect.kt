package com.example.circuler.presentation.ui.request

sealed class RequestSideEffect {
    data class ShowToast(val message: String) : RequestSideEffect()
    data class NavigateToEnterPackaging(
        val requestId: Int
    ) : RequestSideEffect()
}
