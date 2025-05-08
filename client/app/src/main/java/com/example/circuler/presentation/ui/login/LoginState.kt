package com.example.circuler.presentation.ui.login

import com.example.circuler.presentation.core.util.UiState

data class LoginState(
    val uiState: UiState<String> = UiState.Loading
)
