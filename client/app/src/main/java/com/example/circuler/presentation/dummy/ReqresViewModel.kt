package com.example.circuler.presentation.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.repository.ReqresRepository
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
class ReqresViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(ReqresState())
    val state: StateFlow<ReqresState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<ReqresSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<ReqresSideEffect>
        get() = _sideEffect.asSharedFlow()

    val page: Int = 2

    fun getMyJogboList() {
        viewModelScope.launch {
            runCatching {
                reqresRepository.getReqresLists(page)
            }.onSuccess { reqresList ->
                _state.value = _state.value.copy(uiState = UiState.Success("성공"))
                _sideEffect.emit(ReqresSideEffect.ShowToast("데이터 불러오기 성공~!"))

            }.onFailure { error ->
                _state.value = _state.value.copy(uiState = UiState.Failure)
                _sideEffect.emit(ReqresSideEffect.ShowToast("데이터 불러오기 실패ㅠㅠ"))
                Timber.e(error)
            }
        }
    }
}