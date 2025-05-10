package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.SubmissionPackagingEntity

interface SubmissionRepository {
    suspend fun postPackageRequest(requestId: Int, submissionData: SubmissionPackagingEntity): Result<Unit>
}