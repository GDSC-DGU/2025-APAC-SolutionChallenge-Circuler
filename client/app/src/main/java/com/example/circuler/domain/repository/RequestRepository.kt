package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.AddPackagingEntity

interface RequestRepository {
    suspend fun postPackage(requestPackageData: AddPackagingEntity): Result<Unit>
}