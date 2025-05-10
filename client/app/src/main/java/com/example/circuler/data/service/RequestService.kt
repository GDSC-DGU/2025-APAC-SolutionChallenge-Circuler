package com.example.circuler.data.service

import com.example.circuler.data.dto.request.RequestPackageDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponsePackagingListDto
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzQ2OTAwNTE5LCJleHAiOjE3NDY5MDIzMTksImF1dGgiOiJST0xFX1VTRVIifQ.jtPB76Gg-2tNdkcrQGohNkkz6_XNFmXmu_6RWlIravI"

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
}
