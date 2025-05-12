package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.DeliveryEntity

interface DeliveryRepository {
    suspend fun postDeliveryRequest(deliveryId: Int): Result<Unit>
    suspend fun getDeliveryPending(): Result<List<DeliveryEntity>>
}
