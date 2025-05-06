package com.example.circuler.presentation.ui.add

import com.example.circuler.presentation.core.util.UiState

data class AddPackagingState(
    val uiState: UiState<String> = UiState.Loading,
    // todo: uistate 안으로 옯기기 post entity 확인
    val location: String = "",
    val quantity: String = ""
)
