package com.example.circuler.presentation.ui.submit

import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.presentation.core.util.UiState

data class SubmitPackagingState(
    val uiState: UiState<List<PackageListCardWithMethodEntity>> = UiState.Loading
)
