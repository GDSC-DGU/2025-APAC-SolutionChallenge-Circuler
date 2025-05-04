package com.example.circuler.domain.entity

data class HistoryDataEntity(
    val code: String,
    val isSuccess: String,
    val results: List<ListCardWithMethodEntity>
)