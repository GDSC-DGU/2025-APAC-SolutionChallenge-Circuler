package com.example.circuler.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRequestPackageDto(
    @SerialName("id")
    val id: Int
)
