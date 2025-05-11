package com.example.circuler.presentation.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.data.datasource.GoogleDataSource
import com.example.circuler.domain.repository.DeliveryRepository
import com.example.circuler.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<LoginSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<LoginSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun startGoogleLogin() =
        viewModelScope.launch {
            _sideEffect.emit(LoginSideEffect.StartGoogleLogin)
        }

    fun handleLoginResult(context: Context) {
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    val token = GoogleDataSource.signInWithGoogle(context)

                    if (token != null) {
                        Timber.tag("GoogleLogin").d("success -> $token")
                        sendTokenToServer(token)
                    }
                } catch (e: Exception) {
                    Timber.tag("GoogleLogin").d("error -> $e")
                }
            }
        }
    }

    private fun handleLoginError(errorMessage: String) {
        viewModelScope.launch {
            _sideEffect.emit(LoginSideEffect.LoginError(errorMessage))
        }
    }

    private fun sendTokenToServer(
        accessToken: String
    ) {
        viewModelScope.launch {
//            userRepository.getAccessToken()
//                .onSuccess { response ->
//                   token 저장
//            tokenRepository.setTokens(response.accessToken, response.refreshToken)
//                   loginsuccess sideEffect
//                }.onFailure { throwable ->
//                    val errorMessage = throwable.localizedMessage ?: "Unknown error"
//                    handleLoginError(errorMessage)
//                }

            _sideEffect.emit(
                LoginSideEffect.LoginSuccess(
                    accessToken
                )
            )
        }
    }
}
