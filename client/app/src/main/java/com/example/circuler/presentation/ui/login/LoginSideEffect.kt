package com.example.circuler.presentation.ui.login

sealed class LoginSideEffect {
    data class ShowToast(val message: String) : LoginSideEffect()
    data object StartGoogleLogin : LoginSideEffect()
    data class LoginSuccess(val accessToken: String) : LoginSideEffect()
    data class LoginError(val errorMessage: String) : LoginSideEffect()
}
