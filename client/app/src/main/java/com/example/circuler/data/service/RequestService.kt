package com.example.circuler.data.service

import com.example.circuler.BuildConfig.TOKEN
import com.example.circuler.data.dto.request.RequestPackageDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponsePackageMyDto
import com.example.circuler.data.dto.response.ResponsePackagingListDto
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestService {
    @GET("/api/v0/packagingRequest")
    suspend fun getPackagingRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN"
    ): BaseResponse<List<ResponsePackagingListDto>>

    @POST("/api/v0/packagingRequest")
    suspend fun postPackagingRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Body body: RequestPackageDto
    ): BaseResponse<ResponseRequestPackageDto>

    @GET("/api/v0/packagingRequest/{packagingRequestId}")
    suspend fun getPackagingRequestDetail(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("packagingRequestId") packagingRequestId: Int
    ): BaseResponse<ResponsePackagingListDto>

    @GET("/api/v0/packagingRequest/my")
    suspend fun getPackagingRequestMy(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN"
    ): List<ResponsePackageMyDto>
}
