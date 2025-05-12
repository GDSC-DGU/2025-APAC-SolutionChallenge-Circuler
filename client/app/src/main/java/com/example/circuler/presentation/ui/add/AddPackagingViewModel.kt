package com.example.circuler.presentation.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.repository.RequestRepository
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
class AddPackagingViewModel @Inject constructor(
    private val requestRepository: RequestRepository
) : ViewModel() {
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

    fun updatedType(type: String) {
        _state.value = _state.value.copy(
            uiState = _state.value.uiState.copy(
                type = type
            )
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

    fun postPackageRequest() = viewModelScope.launch {
        requestRepository.postPackage(_state.value.uiState)
            .onSuccess {
                Timber.tag("postpackage").d("success")
                _sideEffect.emit(AddPackagingSideEffect.NavigateToHome)
                _sideEffect.emit(AddPackagingSideEffect.ShowToast("Your request has been received"))
            }
            .onFailure { error ->
                Timber.e(error)
                _sideEffect.emit(AddPackagingSideEffect.ShowToast("Your request has been failed"))
            }
    }
}
