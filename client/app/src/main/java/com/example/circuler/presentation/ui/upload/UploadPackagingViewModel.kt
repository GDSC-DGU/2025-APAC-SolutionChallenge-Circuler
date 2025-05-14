package com.example.circuler.presentation.ui.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.PackageImageEntity
import com.example.circuler.domain.repository.SubmissionRepository
import com.example.circuler.presentation.core.util.ImageUiState
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
class UploadPackagingViewModel @Inject constructor(
    private val submissionRepository: SubmissionRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(UploadPackagingState())
    val state: StateFlow<UploadPackagingState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<UploadPackagingSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<UploadPackagingSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToHome() =
        viewModelScope.launch {
            _sideEffect.emit(
                UploadPackagingSideEffect.NavigateToHome
            )
        }

    fun updatePermissionGranted() = viewModelScope.launch {
        _state.value = _state.value.copy(uiState = ImageUiState.Idle)
    }

    fun updatePermissionNotGranted() = viewModelScope.launch {
        _state.value = _state.value.copy(uiState = ImageUiState.PermissionNotGranted)
    }

    fun postPackageImage(submissionId: Int, imageUri: Uri) = viewModelScope.launch {
        _state.value = _state.value.copy(uiState = ImageUiState.Loading)

        submissionRepository.postPackageImage(
            submissionId = submissionId,
            image = imageUri.toString()
        )
            .onSuccess { response ->
                val data = PackageImageEntity(
                    match = response.match
                )
                Timber.tag("postPackageImage").d("image success")
                _state.value = _state.value.copy(uiState = ImageUiState.Success(data = data))
            }
            .onFailure { error ->
                Timber.e(error)
                _state.value = _state.value.copy(uiState = ImageUiState.Failure)
            }
    }
}
