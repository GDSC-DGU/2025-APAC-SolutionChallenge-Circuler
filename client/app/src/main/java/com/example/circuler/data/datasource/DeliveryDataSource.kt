package com.example.circuler.data.datasource

import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponseDeliveryDto
import com.example.circuler.data.dto.response.ResponseEmptyDto
import com.example.circuler.data.service.DeliveryService
import javax.inject.Inject

internal class DeliveryDataSource @Inject constructor(
    private val deliveryService: DeliveryService
) {
    suspend fun postDeliveryRequest(deliveryId: Int): ResponseEmptyDto =
        deliveryService.postDeliveryRequest(deliveryId = deliveryId)

    suspend fun getDeliveryPending(): BaseResponse<List<ResponseDeliveryDto>> =
        deliveryService.getDeliveryPending()
}
