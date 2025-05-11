package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.SubmissionDataSource
import com.example.circuler.data.dto.request.toDto
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.entity.SubmissionPackagingEntity
import com.example.circuler.domain.repository.SubmissionRepository
import javax.inject.Inject

internal class SubmissionRepositoryImpl @Inject constructor(
    private val submissionDataSource: SubmissionDataSource
) : SubmissionRepository {
    override suspend fun postPackageRequest(requestId: Int, submissionData: SubmissionPackagingEntity): Result<Unit> =
        runCatching {
            submissionDataSource.postPackageRequest(
                requestId = requestId,
                body = submissionData.toDto()
            )
        }

    override suspend fun postPackagingDelivery(requestId: Int): Result<Unit> =
        runCatching {
            submissionDataSource.postPackagingDelivery(
                requestId = requestId
            )
        }

    override suspend fun getSubmittedData(requestId: Int): Result<List<PackageListCardWithMethodEntity>> =
        runCatching {
            submissionDataSource.getSubmittedData(
                requestId = requestId
            ).result.map {
                it.toEntity()
            }
        }
}
