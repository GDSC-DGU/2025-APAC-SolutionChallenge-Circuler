package com.example.circuler.presentation.ui.delivery

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
class DeliveryViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(DeliveryState())
    val state: StateFlow<DeliveryState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<DeliverySideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<DeliverySideEffect>
        get() = _sideEffect.asSharedFlow()
}
