package com.example.circuler.presentation.ui.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RequestViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(RequestState())
    val state: StateFlow<RequestState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<RequestSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<RequestSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToConfirmPackaging() =
        viewModelScope.launch {
            _sideEffect.emit(
                RequestSideEffect.NavigateToConfirmPackaging
            )
        }
}
