package com.example.circuler.data.repositoryimpl

import com.example.circuler.data.datalocal.datasource.TokenDataSource
import com.example.circuler.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {
    override fun getAccessToken(): String = tokenDataSource.accessToken
    override fun getRefreshToken(): String = tokenDataSource.refreshToken

    override fun setTokens(accessToken: String, refreshToken: String) {
        tokenDataSource.accessToken = accessToken
        tokenDataSource.refreshToken = refreshToken
    }

    override fun clearInfo() {
        tokenDataSource.clearInfo()
    }
}
