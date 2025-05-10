package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.TokenEntity

interface UserRepository {
    suspend fun getAccessToken(): Result<TokenEntity>
}