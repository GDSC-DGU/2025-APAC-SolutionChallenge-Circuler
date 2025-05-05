package com.example.circuler.presentation.ui.delivery

sealed class DeliverySideEffect {
    data class ShowToast(val message: String) : DeliverySideEffect()
}
