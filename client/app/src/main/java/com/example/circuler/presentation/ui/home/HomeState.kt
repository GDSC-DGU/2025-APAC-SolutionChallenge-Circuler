package com.example.circuler.presentation.ui.home

import com.example.circuler.presentation.core.util.UiState

data class HomeState(
    val uiState: UiState<String> = UiState.Loading
)
