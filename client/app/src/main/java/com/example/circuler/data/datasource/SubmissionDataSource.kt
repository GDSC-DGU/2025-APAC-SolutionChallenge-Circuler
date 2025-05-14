package com.example.circuler.data.datasource

import com.example.circuler.data.dto.request.RequestPackageSubmissionDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import com.example.circuler.data.service.SubmissionService
import okhttp3.MultipartBody
import javax.inject.Inject

internal class SubmissionDataSource @Inject constructor(
    private val submissionService: SubmissionService
) {
    suspend fun postPackageRequest(requestId: Int, body: RequestPackageSubmissionDto): BaseResponse<ResponseRequestPackageDto> = submissionService.postPackagingRequest(packagingRequestId = requestId, body = body)
    suspend fun postPackagingDelivery(requestId: Int) = submissionService.postPackagingDelivery(packageSubmissionId = requestId)
    suspend fun getSubmittedData(requestId: Int) = submissionService.getSubmittedData(packagingRequestId = requestId)
    suspend fun getPackageAccept(requestId: Int, submissionId: Int) = submissionService.getPackageAccept(packagingRequestId = requestId, packageSubmissionId = submissionId)
    suspend fun postPackageImage(submissionId: Int, image: MultipartBody.Part?) = submissionService.postPackageImage(packageSubmissionId = submissionId, image = image)
}
