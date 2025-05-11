package com.example.circuler.data.datasource

import com.example.circuler.data.dto.request.RequestPackageSubmissionDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import com.example.circuler.data.service.SubmissionService
import javax.inject.Inject

internal class SubmissionDataSource @Inject constructor(
    private val submissionService: SubmissionService
) {
    suspend fun postPackageRequest(requestId: Int, body: RequestPackageSubmissionDto): BaseResponse<ResponseRequestPackageDto> = submissionService.postPackagingRequest(packagingRequestId = requestId, body = body)
    suspend fun postPackagingDelivery(requestId: Int) = submissionService.postPackagingDelivery(packageSubmissionId = requestId)
    suspend fun getHistoryData(requestId: Int) = submissionService.getHistoryData(packagingRequestId = requestId)
}
