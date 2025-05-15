package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.PackageImageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePackageImageDto(
    @SerialName("match")
    val match: Boolean
) {
    fun toEntity() = PackageImageEntity(
        match = match
    )
}
