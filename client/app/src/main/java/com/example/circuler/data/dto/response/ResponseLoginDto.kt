package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.TokenEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("type")
    val type: String
) {
    fun toEntity() =
        TokenEntity(
            accessToken = accessToken,
            refreshToken = refreshToken,
            type = type
        )
}