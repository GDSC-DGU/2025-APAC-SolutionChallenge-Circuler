package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.DeliveryDataSource
import com.example.circuler.domain.entity.DeliveryEntity
import com.example.circuler.domain.repository.DeliveryRepository
import javax.inject.Inject

internal class DeliveryRepositoryImpl @Inject constructor(
    private val deliveryDataSource: DeliveryDataSource
) : DeliveryRepository {
    override suspend fun getDeliveryPending(): Result<List<DeliveryEntity>> =
        runCatching {
            deliveryDataSource.getDeliveryPending().result.map { it.toEntity() }
        }
}
