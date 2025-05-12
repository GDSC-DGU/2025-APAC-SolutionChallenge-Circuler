package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSubmittedDataDto(
    @SerialName("id")
    val id: Int,
    @SerialName("location")
    val location: String,
    @SerialName("method")
    val method: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("status")
    val status: String
) {
    fun toEntity() =
        PackageListCardWithMethodEntity(
            id = id,
            location = location,
            method = method,
            quantity = quantity,
            status = status
        )
}
