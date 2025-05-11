package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.AddPackagingEntity
import com.example.circuler.domain.entity.PackageListCardEntity
import com.example.circuler.domain.entity.PackageMyEntity

interface RequestRepository {
    suspend fun getPackages(): Result<List<PackageListCardEntity>>
    suspend fun postPackage(requestPackageData: AddPackagingEntity): Result<Unit>
    suspend fun getPackageDetail(requestId: Int): Result<PackageListCardEntity>
    suspend fun getPackagingRequestMy(): Result<List<PackageMyEntity>>
}
