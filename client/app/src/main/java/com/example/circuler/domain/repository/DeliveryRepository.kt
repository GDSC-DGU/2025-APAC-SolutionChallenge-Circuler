package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.DeliveryEntity

interface DeliveryRepository {
    suspend fun getDeliveryPending(): Result<List<DeliveryEntity>>
}
