package com.example.circuler.data.service

import com.example.circuler.data.dto.request.RequestPackageSubmissionDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface SubmissionService {
    @POST("/api/v0/packageSubmission/{packagingRequestId}")
    suspend fun postPackagingRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packagingRequestId") packagingRequestId: Int,
        @Body body: RequestPackageSubmissionDto
    ): BaseResponse<ResponseRequestPackageDto>
}