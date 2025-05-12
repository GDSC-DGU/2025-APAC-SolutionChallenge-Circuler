package com.example.circuler.presentation.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circuler.domain.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository,
) : ViewModel() {
    // state 관리
    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState>
        get() = _state.asStateFlow()

    // side effect 관리
    private val _sideEffect: MutableSharedFlow<MapSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<MapSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun navigateToHome() =
        viewModelScope.launch {
            _sideEffect.emit(
                MapSideEffect.NavigateToHome
            )
        }

    fun postDeliveryRequest() = viewModelScope.launch {
        _sideEffect.emit(MapSideEffect.ShowToast("Your request has been received"))
        _sideEffect.emit(MapSideEffect.ShowToast("Your request has been failed"))
    }
}
