package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.RequestDataSource
import com.example.circuler.data.dto.request.toDto
import com.example.circuler.domain.entity.AddPackagingEntity
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.domain.repository.RequestRepository
import javax.inject.Inject

internal class RequestRepositoryImpl @Inject constructor(
    private val requestDataSource: RequestDataSource
) : RequestRepository {
    override suspend fun getPackages(): Result<List<PackageListCardEntity>> =
        runCatching {
            requestDataSource.getPackagingRequest().result.map { it.toEntity() }
        }

    override suspend fun postPackage(
        requestPackageData: AddPackagingEntity
    ): Result<Unit> = runCatching {
        requestDataSource.postPackagingRequest(
            body = requestPackageData.toDto()
        )
    }

    override suspend fun getPackageDetail(requestId: Int): Result<PackageListCardEntity> =
        runCatching {
            requestDataSource.getPackagingRequestDetail(requestId = requestId).result.toEntity()
        }
}
