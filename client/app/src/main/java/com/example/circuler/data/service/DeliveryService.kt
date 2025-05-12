package com.example.circuler.data.service

import com.example.circuler.BuildConfig.TOKEN
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseDeliveryDto
import com.example.circuler.data.dto.response.ResponseEmptyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DeliveryService {
    @POST("/api/v0/delivery/apply/{deliveryId}")
    suspend fun postDeliveryRequest(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        @Path("deliveryId") deliveryId: Int
    ): ResponseEmptyDto

    @GET("/api/v0/delivery/pending")
    suspend fun getDeliveryPending(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN"
    ): BaseResponse<List<ResponseDeliveryDto>>
}
