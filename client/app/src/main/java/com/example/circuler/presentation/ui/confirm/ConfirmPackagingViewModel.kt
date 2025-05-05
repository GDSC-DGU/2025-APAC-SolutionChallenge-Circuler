package com.example.circuler.presentation.ui.confirm

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
class ConfirmPackagingViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(ConfirmPackagingState())
    val state: StateFlow<ConfirmPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<ConfirmPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<ConfirmPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToUploadPackage() =
        viewModelScope.launch {
            _sideEffect.emit(
                ConfirmPackagingSideEffect.NavigateToUploadPackage
            )
        }
}
