package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.RequestDataSource
import com.example.circuler.data.dto.request.toDto
import com.example.circuler.domain.entity.AddPackagingEntity
import com.example.circuler.domain.repository.RequestRepository
import javax.inject.Inject

internal class RequestRepositoryImpl @Inject constructor(
    private val requestDataSource: RequestDataSource
) : RequestRepository {
    override suspend fun postPackage(
        requestPackageData: AddPackagingEntity
    ): Result<Unit> = runCatching {
        requestDataSource.postPackagingRequest(
            body = requestPackageData.toDto()
        )
    }
}
