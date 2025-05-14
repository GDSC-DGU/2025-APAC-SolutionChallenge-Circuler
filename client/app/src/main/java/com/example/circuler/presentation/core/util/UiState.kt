package com.example.circuler.presentation.core.util

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data object Failure : UiState<Nothing>()
}

sealed class EmptyUiState<out T> {
    data object Loading : EmptyUiState<Nothing>()
    data object Empty : EmptyUiState<Nothing>()
    data class Success<T>(val data: T) : EmptyUiState<T>()
    data object Failure : EmptyUiState<Nothing>()
}

sealed class ImageUiState<out T> {
    data object Idle : ImageUiState<Nothing>()
    data object Loading : ImageUiState<Nothing>()
    data class Success<T>(val data: T) : ImageUiState<T>()
    data object Failure : ImageUiState<Nothing>()
    data object PermissionNotGranted : ImageUiState<Nothing>()
}
