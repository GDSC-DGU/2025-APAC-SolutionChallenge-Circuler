package com.example.circuler.presentation.ui.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AddPackagingViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(AddPackagingState())
    val state: StateFlow<AddPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<AddPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<AddPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()
}
