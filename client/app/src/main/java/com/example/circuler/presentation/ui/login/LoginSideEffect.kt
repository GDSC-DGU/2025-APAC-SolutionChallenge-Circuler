package com.example.circuler.presentation.ui.login

sealed class LoginSideEffect {
    data class ShowToast(val message: String) : LoginSideEffect()
    data object NavigateToHome : LoginSideEffect()
}
