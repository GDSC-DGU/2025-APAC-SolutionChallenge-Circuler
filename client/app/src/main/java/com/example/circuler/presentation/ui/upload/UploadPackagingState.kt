package com.example.circuler.presentation.ui.upload

import com.example.circuler.presentation.core.util.ImageUiState

data class UploadPackagingState(
    val uiState: ImageUiState<String> = ImageUiState.Idle
)
