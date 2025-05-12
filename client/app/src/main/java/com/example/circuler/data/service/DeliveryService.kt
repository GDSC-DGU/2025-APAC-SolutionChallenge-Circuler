package com.example.circuler.data.service

import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseDeliveryDto
import retrofit2.http.GET
import retrofit2.http.Header

interface DeliveryService {
    @GET("/api/v0/delivery/pending")
    suspend fun getDeliveryPending(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN"
    ): BaseResponse<List<ResponseDeliveryDto>>
}
