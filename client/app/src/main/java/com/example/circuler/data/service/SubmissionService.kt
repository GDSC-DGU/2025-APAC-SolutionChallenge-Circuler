package com.example.circuler.data.service

import com.example.circuler.BuildConfig.TOKEN
import com.example.circuler.data.dto.request.RequestPackageSubmissionDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseAcceptDto
import com.example.circuler.data.dto.response.ResponseEmptyDto
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import com.example.circuler.data.dto.response.ResponseSubmittedDataDto
import retrofit2.http.Body
import retrofit2.http.GET
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

    @POST("/api/v0/packageSubmission/{packageSubmissionId}/delivered")
    suspend fun postPackagingDelivery(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packageSubmissionId") packageSubmissionId: Int
    ): ResponseEmptyDto

    @GET("/api/v0/packageSubmission/{packagingRequestId}/submissions")
    suspend fun getSubmittedData(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packagingRequestId") packagingRequestId: Int
    ): BaseResponse<List<ResponseSubmittedDataDto>>

    @GET("/api/v0/packageSubmission/{packagingRequestId}/submissions/{packageSubmissionId}/accept")
    suspend fun getPackageAccept(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packagingRequestId") packagingRequestId: Int,
        @Path("packageSubmissionId") packageSubmissionId: Int
    ): BaseResponse<ResponseAcceptDto>
}
