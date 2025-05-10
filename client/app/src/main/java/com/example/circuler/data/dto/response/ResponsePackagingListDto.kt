package com.example.circuler.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePackagingListDto(
    @SerialName("result")
    val result: List<Result>
) {
    @Serializable
    data class Result(
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
    )
}