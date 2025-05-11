package com.example.circuler.presentation.ui.submit

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
                //todo: 버튼색 조절
            }
            .onFailure { error ->
                Timber.e(error)
            }
    }
}
