package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.PackageListCardEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePackagingListDto(
    @SerialName("distance")
    val distance: String,
    @SerialName("id")
    val id: Int,
    @SerialName("location")
    val location: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("type")
    val type: String
) {
    fun toEntity() = PackageListCardEntity(
        distance = distance,
        id = id,
        location = location,
        quantity = quantity,
        type = type
    )
}
