package com.example.circuler.presentation.ui.delivery

import com.example.circuler.domain.entity.DeliveryEntity
import com.example.circuler.presentation.core.util.UiState

data class DeliveryState(
    val uiState: UiState<List<DeliveryEntity>> = UiState.Loading
)
