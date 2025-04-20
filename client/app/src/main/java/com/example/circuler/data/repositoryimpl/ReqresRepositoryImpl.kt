package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.ReqresDataSource
import com.example.circuler.domain.entity.ReqresEntity
import com.example.circuler.domain.repository.ReqresRepository
import javax.inject.Inject

internal class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource
) : ReqresRepository {
    override suspend fun getReqresLists(page: Int): Result<List<ReqresEntity>> =
        runCatching {
            reqresDataSource.getReqresLists(page).data.map {
                it.toEntity()
            }
        }
}