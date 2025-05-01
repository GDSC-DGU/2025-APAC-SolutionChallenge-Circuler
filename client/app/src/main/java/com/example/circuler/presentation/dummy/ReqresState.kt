package com.example.circuler.presentation.dummy

import com.example.circuler.presentation.core.util.UiState

data class ReqresState(
    val uiState: UiState<String> = UiState.Loading
)
