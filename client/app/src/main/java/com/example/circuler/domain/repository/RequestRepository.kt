package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.AddPackagingEntity
import com.example.circuler.domain.entity.PackageListCardEntity

interface RequestRepository {
    suspend fun getPackages(): Result<List<PackageListCardEntity>>
    suspend fun postPackage(requestPackageData: AddPackagingEntity): Result<Unit>
}
