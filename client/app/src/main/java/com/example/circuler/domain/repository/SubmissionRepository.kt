package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.AcceptEntity
import com.example.circuler.domain.entity.PackageImageEntity
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.entity.SubmissionPackagingEntity
import java.io.File

interface SubmissionRepository {
    suspend fun postPackageRequest(requestId: Int, submissionData: SubmissionPackagingEntity): Result<Unit>
    suspend fun postPackagingDelivery(requestId: Int): Result<Unit>
    suspend fun getSubmittedData(requestId: Int): Result<List<PackageListCardWithMethodEntity>>
    suspend fun getPackageAccept(requestId: Int, submissionId: Int): Result<AcceptEntity>
    suspend fun postPackageImage(submissionId: Int, file: File?): Result<PackageImageEntity>
}
