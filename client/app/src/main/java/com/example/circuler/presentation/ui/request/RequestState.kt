package com.example.circuler.presentation.ui.request

import com.example.circuler.presentation.core.util.UiState

data class RequestState(
    val uiState: UiState<String> = UiState.Loading
)
