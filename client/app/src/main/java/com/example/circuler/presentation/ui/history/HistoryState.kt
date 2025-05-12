package com.example.circuler.presentation.ui.history

import com.example.circuler.domain.entity.PackageMyEntity
import com.example.circuler.presentation.core.util.UiState

data class HistoryState(
    val uiState: UiState<List<PackageMyEntity>> = UiState.Loading
)
