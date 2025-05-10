package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datasource.UserDataSource
import com.example.circuler.domain.entity.TokenEntity
import com.example.circuler.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getAccessToken(): Result<TokenEntity> =
        runCatching {
            userDataSource.getAccessToken().toEntity()
        }
}
