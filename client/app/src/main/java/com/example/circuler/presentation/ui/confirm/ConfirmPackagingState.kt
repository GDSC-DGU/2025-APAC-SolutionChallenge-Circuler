package com.example.circuler.presentation.ui.confirm

import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.presentation.core.util.UiState

data class ConfirmPackagingState(
    val uiState: UiState<PackageListCardEntity> = UiState.Loading
)
