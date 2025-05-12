package com.example.circuler.data.dto.request

import com.example.circuler.domain.entity.SubmissionPackagingEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPackageSubmissionDto(
    @SerialName("location")
    val location: String,
    @SerialName("method")
    val method: String,
    @SerialName("type")
    val type: String
)

fun SubmissionPackagingEntity.toDto() =
    RequestPackageSubmissionDto(
        location = location,
        method = method,
        type = type
    )
