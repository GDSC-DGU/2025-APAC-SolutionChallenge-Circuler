package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.entity.SubmissionPackagingEntity

interface SubmissionRepository {
    suspend fun postPackageRequest(requestId: Int, submissionData: SubmissionPackagingEntity): Result<Unit>
    suspend fun postPackagingDelivery(requestId: Int): Result<Unit>
    suspend fun getHistoryData(requestId: Int): Result<List<PackageListCardWithMethodEntity>>
}