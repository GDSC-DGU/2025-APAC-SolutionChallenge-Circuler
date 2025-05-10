package com.example.circuler.domain.entity

data class PackageListCardEntity(
    val distance: String = "",
    val id: Int = 1,
    val location: String = "",
    val quantity: Int = 1,
    val type: String = ""
)

//todo: dummy 모두 지우고 삭제
data class ListCardEntity(
    val distance: String,
    val id: String,
    val location: String,
    val quantity: String,
    val type: String
)
