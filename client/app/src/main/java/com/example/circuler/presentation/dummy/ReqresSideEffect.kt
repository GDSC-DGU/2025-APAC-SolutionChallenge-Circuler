package com.example.circuler.presentation.dummy

sealed class ReqresSideEffect {
    data class ShowToast(val message: String) : ReqresSideEffect()
}