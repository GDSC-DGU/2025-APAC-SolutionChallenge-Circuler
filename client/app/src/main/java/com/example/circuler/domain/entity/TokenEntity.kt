package com.example.circuler.domain.entity

data class TokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val type: String
)
