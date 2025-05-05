package com.example.circuler.presentation.ui.history

import com.example.circuler.domain.entity.HistoryDataEntity
import com.example.circuler.domain.entity.ListCardWithMethodEntity

data class HistoryState(
    val uiState: HistoryDataEntity = HistoryDataEntity(
        code = "",
        isSuccess = "",
        results = listOf(
            ListCardWithMethodEntity(
                id = "1",
                location = "123-456",
                method = "plastic",
                quantity = "6"
            )
        )
    )
)
