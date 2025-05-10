package com.example.circuler.data.dto.request

import com.example.circuler.domain.entity.AddPackagingEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPackageDto(
    @SerialName("location")
    val location: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("type")
    val type: String
)

fun AddPackagingEntity.toDto() =
    RequestPackageDto(
        location = location,
        quantity = quantity.toInt(),
        type = type
    )
