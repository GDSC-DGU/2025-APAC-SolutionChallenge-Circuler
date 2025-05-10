package com.example.circuler.presentation.ui.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.domain.repository.RequestRepository
import com.example.circuler.presentation.core.util.UiState
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
class ConfirmPackagingViewModel @Inject constructor(
    private val requestRepository: RequestRepository
) : ViewModel() {
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

    suspend fun getPackageDetail(requestId: Int) {
        requestRepository.getPackageDetail(requestId = requestId)
            .onSuccess { response ->
                val data = response.let { item ->
                    PackageListCardEntity(
                        distance = item.distance,
                        id = item.id,
                        location = item.location,
                        quantity = item.quantity,
                        type = item.type
                    )
                }
                Timber.tag("getPackageDetail").d("success")
                _state.value = _state.value.copy(uiState = UiState.Success(data))
            }
            .onFailure { error ->
                _state.value = _state.value.copy(uiState = UiState.Failure)
                Timber.e(error)
            }
    }

}
