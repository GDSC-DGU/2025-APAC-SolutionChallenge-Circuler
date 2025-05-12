package com.example.circuler.presentation.ui.request

import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.presentation.core.util.EmptyUiState

data class RequestState(
    val uiState: EmptyUiState<List<PackageListCardEntity>> = EmptyUiState.Loading
)
