package com.example.circuler.data.service

import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseDeliveryDto
import retrofit2.http.GET
import retrofit2.http.Header

interface DeliveryService {
    //todo: 배달 매칭 (map에서)

    @GET("/api/v0/delivery/pending")
    suspend fun getDeliveryPending(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
    ): BaseResponse<List<ResponseDeliveryDto>>

    //todo: 배달 대기 상세 조회 (map에서)
}
