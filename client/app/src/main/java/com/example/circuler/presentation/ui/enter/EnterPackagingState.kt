package com.example.circuler.presentation.ui.enter

import com.example.circuler.domain.entity.AddPackagingEntity

data class EnterPackagingState(
    val uiState: AddPackagingEntity = AddPackagingEntity(
        location = "",
        quantity = "",
        type = ""
    ),
    val isOpenPackageBottomSheet: Boolean = false,
    val selectedPackageIndex: Int = 0,
    val isOpenDeliveryBottomSheet: Boolean = false,
    val selectedDeliveryIndex: Int = 0,
)
