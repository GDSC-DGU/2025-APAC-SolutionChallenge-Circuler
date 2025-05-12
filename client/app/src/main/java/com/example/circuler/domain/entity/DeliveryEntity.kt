package com.example.circuler.domain.entity

data class DeliveryEntity(
    val deliveryId: Int,
    val packagingType: String,
    val requestLocation: String,
    val storemanName: String,
    val submissionLocation: String,
    val submissionQuantity: Int,
    val userName: String
)
