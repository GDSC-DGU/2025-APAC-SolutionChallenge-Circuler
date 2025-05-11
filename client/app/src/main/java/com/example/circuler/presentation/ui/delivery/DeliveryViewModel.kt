package com.example.circuler.presentation.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.entity.DeliveryEntity
import com.example.circuler.domain.repository.DeliveryRepository
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
class DeliveryViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(DeliveryState())
    val state: StateFlow<DeliveryState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<DeliverySideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<DeliverySideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToMap() =
        viewModelScope.launch {
            _sideEffect.emit(
                DeliverySideEffect.NavigateToMap
            )
        }

    suspend fun getDeliveryPending() {
        deliveryRepository.getDeliveryPending()
            .onSuccess { response ->
                val listData = response.map { item ->
                    DeliveryEntity(
                        deliveryId = item.deliveryId,
                        packagingType = item.packagingType,
                        requestLocation = item.requestLocation,
                        storemanName = item.storemanName,
                        submissionLocation = item.submissionLocation,
                        submissionQuantity = item.submissionQuantity,
                        userName = item.userName
                    )
                }

                _state.value = _state.value.copy(uiState = UiState.Success(listData))
                Timber.tag("getDeliveryPending").d("success")
            }
            .onFailure { error ->
                _state.value = _state.value.copy(uiState = UiState.Failure)
                Timber.e(error)
            }
    }
}
