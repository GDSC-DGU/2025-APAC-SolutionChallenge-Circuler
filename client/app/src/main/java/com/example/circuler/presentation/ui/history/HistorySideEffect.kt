package com.example.circuler.presentation.ui.history

sealed class HistorySideEffect {
    data class ShowToast(val message: String) : HistorySideEffect()
}
