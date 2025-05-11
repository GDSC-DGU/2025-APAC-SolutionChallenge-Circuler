package com.example.circuler.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.PackageMyEntity
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
class HistoryViewModel @Inject constructor(
    private val requestRepository: RequestRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<HistorySideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<HistorySideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToSubmit(requestId: Int) =
        viewModelScope.launch {
            _sideEffect.emit(
                HistorySideEffect.NavigateToSubmit(
                    requestId = requestId
                )
            )
        }

    suspend fun getPackagingRequestMy() {
        requestRepository.getPackagingRequestMy()
            .onSuccess { response ->
                val listData = response.map { item ->
                    PackageMyEntity(
                        createdAt = item.createdAt,
                        packagingType = item.packagingType,
                        requestId = item.requestId,
                        status = item.status,
                        location = item.location,
                        quantity = item.quantity,
                    )
                }

                _state.value = _state.value.copy(uiState = UiState.Success(listData))

                Timber.tag("getPackagingRequestMy").d("success")
            }
            .onFailure { error ->
                _state.value = _state.value.copy(uiState = UiState.Failure)
                Timber.e(error)
            }
    }
}
