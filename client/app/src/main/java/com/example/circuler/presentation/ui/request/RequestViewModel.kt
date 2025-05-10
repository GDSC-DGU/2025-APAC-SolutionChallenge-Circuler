package com.example.circuler.presentation.ui.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.domain.repository.RequestRepository
import com.example.circuler.presentation.core.util.EmptyUiState
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
class RequestViewModel @Inject constructor(
    private val requestRepository: RequestRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(RequestState())
    val state: StateFlow<RequestState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<RequestSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<RequestSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToEnterPackaging(requestId: Int) =
        viewModelScope.launch {
            _sideEffect.emit(
                RequestSideEffect.NavigateToEnterPackaging(
                    requestId = requestId
                )
            )
        }

    suspend fun getPackages() {
        requestRepository.getPackages()
            .onSuccess { response ->
                val listData = response.map { item ->
                    PackageListCardEntity(
                        distance = item.distance,
                        id = item.id,
                        location = item.location,
                        quantity = item.quantity,
                        type = item.type
                    )
                }

                if (response.isEmpty()) {
                    _state.value = _state.value.copy(uiState = EmptyUiState.Empty)
                } else {
                    _state.value = _state.value.copy(uiState = EmptyUiState.Success(listData))
                }

                Timber.tag("getpackage").d("success")
            }
            .onFailure { error ->
                _state.value = _state.value.copy(uiState = EmptyUiState.Failure)
                Timber.e(error)
            }
    }
}
