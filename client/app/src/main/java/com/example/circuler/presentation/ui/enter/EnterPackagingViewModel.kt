package com.example.circuler.presentation.ui.enter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterPackagingViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(EnterPackagingState())
    val state: StateFlow<EnterPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<EnterPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<EnterPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToConfirmPackage() =
        viewModelScope.launch {
            _sideEffect.emit(
                EnterPackagingSideEffect.NavigateToConfirmPackage
            )
        }

    fun updatedLocation(location: String) {
        _state.value = _state.value.copy(
            uiState = _state.value.uiState.copy(
                location = location
            )
        )
    }

    fun updatedQuantity(quantity: String) {
        _state.value = _state.value.copy(
            uiState = _state.value.uiState.copy(
                quantity = quantity
            )
        )
    }

    fun controlBottomSheet() {
        _state.value = _state.value.copy(
            isOpenBottomSheet = !_state.value.isOpenBottomSheet
        )
    }

    fun updateSelectedIndex(index: Int) {
        _state.value = _state.value.copy(selectedIndex = index)
    }

    //todo: post할때 quantity.toInt
}
