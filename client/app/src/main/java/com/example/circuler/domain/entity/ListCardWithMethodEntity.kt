package com.example.circuler.domain.entity

data class PackageListCardWithMethodEntity(
    val id: Int,
    val location: String,
    val method: String,
    val quantity: Int,
    val status: String
)

//todo: 삭제
data class ListCardWithMethodEntity(
    val id: String,
    val location: String,
    val method: String,
    val quantity: String
)
