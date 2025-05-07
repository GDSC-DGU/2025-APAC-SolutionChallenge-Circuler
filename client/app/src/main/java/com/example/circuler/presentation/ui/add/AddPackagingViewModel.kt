package com.example.circuler.presentation.ui.add

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
class AddPackagingViewModel @Inject constructor() : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(AddPackagingState())
    val state: StateFlow<AddPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<AddPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<AddPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToHome() =
        viewModelScope.launch {
            _sideEffect.emit(
                AddPackagingSideEffect.NavigateToHome
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
