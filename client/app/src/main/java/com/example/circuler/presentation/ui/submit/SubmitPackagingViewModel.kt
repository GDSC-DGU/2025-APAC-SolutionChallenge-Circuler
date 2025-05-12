package com.example.circuler.presentation.ui.submit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.repository.SubmissionRepository
import com.example.circuler.presentation.core.util.UiState
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
class SubmitPackagingViewModel @Inject constructor(
    private val submissionRepository: SubmissionRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(SubmitPackagingState())
    val state: StateFlow<SubmitPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<SubmitPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SubmitPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun postPackagingDelivery(requestId: Int) = viewModelScope.launch {
        submissionRepository.postPackagingDelivery(requestId = requestId)
            .onSuccess {
                Timber.tag("postPackagingDelivery").d("success")
                _sideEffect.emit(SubmitPackagingSideEffect.ShowToast("Your request has been received!"))
            }
            .onFailure { error ->
                Timber.e(error)
                _sideEffect.emit(SubmitPackagingSideEffect.ShowToast("Your request has been failed"))
            }
    }

    suspend fun getSubmittedData(requestId: Int) {
        submissionRepository.getSubmittedData(requestId = requestId)
            .onSuccess { response ->
                val listData = response.map { item ->
                    PackageListCardWithMethodEntity(
                        id = item.id,
                        location = item.location,
                        method = item.method,
                        quantity = item.quantity,
                        status = item.status
                    )
                }

                Timber.tag("getSubmittedData").d("success")
                _state.value = _state.value.copy(uiState = UiState.Success(listData))
            }
            .onFailure { error ->
                _state.value = _state.value.copy(uiState = UiState.Failure)
                Timber.e(error)
            }
    }

    fun getPackageAccept(requestId: Int, submissionId: Int) = viewModelScope.launch {
        submissionRepository.getPackageAccept(requestId = requestId, submissionId = submissionId)
            .onSuccess {
                Timber.tag("getPackageAccept").d("success")
            }
            .onFailure { error ->
                Timber.e(error)
            }
    }
}
