package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.DeliveryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDeliveryDto(
    @SerialName("deliveryId")
    val deliveryId: Int,
    @SerialName("packagingType")
    val packagingType: String,
    @SerialName("requestLocation")
    val requestLocation: String,
    @SerialName("storemanName")
    val storemanName: String,
    @SerialName("submissionLocation")
    val submissionLocation: String,
    @SerialName("submissionQuantity")
    val submissionQuantity: Int,
    @SerialName("userName")
    val userName: String
) {
    fun toEntity() =
        DeliveryEntity(
            deliveryId = deliveryId,
            packagingType = packagingType,
            requestLocation = requestLocation,
            storemanName = storemanName,
            submissionLocation = submissionLocation,
            submissionQuantity = submissionQuantity,
            userName = userName
        )
}
