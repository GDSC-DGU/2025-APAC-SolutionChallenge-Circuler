package com.example.circuler.presentation.ui.map

import com.example.circuler.presentation.core.util.UiState

data class MapState(
    val uiState: UiState<String> = UiState.Loading
)
