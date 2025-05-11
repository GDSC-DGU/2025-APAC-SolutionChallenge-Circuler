package com.example.circuler.data.dto.response

import com.example.circuler.domain.entity.AcceptEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAcceptDto(
    @SerialName("detailStatus")
    val detailStatus: String,
    @SerialName("packageSubmissionId")
    val packageSubmissionId: Int,
    @SerialName("packagingRequestId")
    val packagingRequestId: Int,
    @SerialName("totalStatus")
    val totalStatus: String
) {
    fun toEntity() = AcceptEntity(
        detailStatus = detailStatus,
        packageSubmissionId = packageSubmissionId,
        packagingRequestId = packagingRequestId,
        totalStatus = totalStatus
    )
}
