package com.example.circuler.presentation.ui.enter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.repository.SubmissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EnterPackagingViewModel @Inject constructor(
    private val submissionRepository: SubmissionRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(EnterPackagingState())
    val state: StateFlow<EnterPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<EnterPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<EnterPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun updatedType(type: String) {
        _state.value = _state.value.copy(
            uiState = _state.value.uiState.copy(
                type = type
            )
        )
    }

    fun updatedMethod(method: String) {
        _state.value = _state.value.copy(
            uiState = _state.value.uiState.copy(
                method = method
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

    fun controlPackageBottomSheet() {
        _state.value = _state.value.copy(
            isOpenPackageBottomSheet = !_state.value.isOpenPackageBottomSheet
        )
    }

    fun updatePackageSelectedIndex(index: Int) {
        _state.value = _state.value.copy(selectedPackageIndex = index)
    }

    fun controlDeliveryBottomSheet() {
        _state.value = _state.value.copy(
            isOpenDeliveryBottomSheet = !_state.value.isOpenDeliveryBottomSheet
        )
    }

    fun updateDeliverySelectedIndex(index: Int) {
        _state.value = _state.value.copy(selectedDeliveryIndex = index)
    }

    fun postPackagingRequest(requestId: Int) = viewModelScope.launch {
        submissionRepository.postPackageRequest(requestId = requestId, submissionData = _state.value.uiState)
            .onSuccess {
                Timber.tag("postPackagingRequest").d("success")
                _sideEffect.emit(
                    EnterPackagingSideEffect.NavigateToConfirmPackage(
                        requestId = requestId
                    )
                )
            }
            .onFailure { error ->
                Timber.e(error)
            }
    }
}
