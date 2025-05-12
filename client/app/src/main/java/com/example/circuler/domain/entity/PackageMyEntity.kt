package com.example.circuler.domain.entity

data class PackageMyEntity(
    val createdAt: String,
    val location: String,
    val packagingType: String,
    val quantity: Int,
    val requestId: Int,
    val status: String
)
