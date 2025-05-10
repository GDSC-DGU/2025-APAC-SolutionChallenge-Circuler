package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.SubmissionDataSource
import com.example.circuler.data.dto.request.toDto
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
}
