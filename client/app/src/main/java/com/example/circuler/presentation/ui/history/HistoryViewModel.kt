package com.example.circuler.presentation.ui.history

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
class HistoryViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<HistorySideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<HistorySideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToSubmit() =
        viewModelScope.launch {
            _sideEffect.emit(
                HistorySideEffect.NavigateToSubmit
            )
        }
}
