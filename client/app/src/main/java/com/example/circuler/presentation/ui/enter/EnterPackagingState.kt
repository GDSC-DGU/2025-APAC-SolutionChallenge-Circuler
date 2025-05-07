package com.example.circuler.presentation.ui.enter

import com.example.circuler.domain.entity.AddPackagingEntity

data class EnterPackagingState(
    val uiState: AddPackagingEntity = AddPackagingEntity(
        location = "",
        quantity = "",
        type = ""
    ),
    val isOpenBottomSheet: Boolean = false,
    val selectedIndex: Int = 0,
)
