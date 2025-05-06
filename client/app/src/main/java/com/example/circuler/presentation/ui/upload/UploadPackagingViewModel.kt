package com.example.circuler.presentation.ui.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.presentation.ui.add.AddPackagingSideEffect
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
class UploadPackagingViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(UploadPackagingState())
    val state: StateFlow<UploadPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<UploadPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<UploadPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToHome() =
        viewModelScope.launch {
            _sideEffect.emit(
                UploadPackagingSideEffect.NavigateToHome
            )
        }
}
