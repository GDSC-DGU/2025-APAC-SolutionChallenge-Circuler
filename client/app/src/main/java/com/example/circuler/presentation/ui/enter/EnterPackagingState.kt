package com.example.circuler.presentation.ui.enter

import com.example.circuler.domain.entity.SubmissionPackagingEntity

data class EnterPackagingState(
    val uiState: SubmissionPackagingEntity = SubmissionPackagingEntity(),
    val isOpenPackageBottomSheet: Boolean = false,
    val selectedPackageIndex: Int = 0,
    val isOpenDeliveryBottomSheet: Boolean = false,
    val selectedDeliveryIndex: Int = 0
)
