package com.example.circuler.presentation.ui.add

import com.example.circuler.domain.entity.AddPackagingEntity

data class AddPackagingState(
    val uiState: AddPackagingEntity = AddPackagingEntity(),
    val isOpenBottomSheet: Boolean = false,
    val selectedIndex: Int = 0
)
