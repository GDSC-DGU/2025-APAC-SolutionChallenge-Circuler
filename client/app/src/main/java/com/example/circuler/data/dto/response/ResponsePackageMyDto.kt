package com.example.circuler.data.dto.response


import com.example.circuler.domain.entity.PackageMyEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePackageMyDto(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("location")
    val location: String,
    @SerialName("packagingType")
    val packagingType: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("requestId")
    val requestId: Int,
    @SerialName("status")
    val status: String
) {
    fun toEntity() =
        PackageMyEntity(
            createdAt = createdAt,
            location = location,
            packagingType = packagingType,
            quantity = quantity,
            requestId = requestId,
            status = status
        )
}