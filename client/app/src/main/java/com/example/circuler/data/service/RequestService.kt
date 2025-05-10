package com.example.circuler.data.service

import com.example.circuler.data.dto.request.RequestPackageDto
import com.example.circuler.data.dto.request.RequestPackageSubmissionDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponsePackagingListDto
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzQ2OTA0MjM3LCJleHAiOjE3NDY5MDYwMzcsImF1dGgiOiJST0xFX1VTRVIifQ.tp98ngUuvVggxvyb1Yy216R9aaS80diN9NYSFIzjNj8"

interface RequestService {
    @GET("/api/v0/packagingRequest")
    suspend fun getPackagingRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
    ): BaseResponse<List<ResponsePackagingListDto>>

    @POST("/api/v0/packagingRequest")
    suspend fun postPackagingRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Body body: RequestPackageDto
    ): BaseResponse<ResponseRequestPackageDto>

    @GET("/api/v0/packagingRequest/{packagingRequestId}")
    suspend fun gettPackagingRequestDetail(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packagingRequestId") packagingRequestId: Int,
    ): BaseResponse<ResponsePackagingListDto>
}
