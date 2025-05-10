package com.example.circuler.data.service

import com.example.circuler.data.dto.response.ResponseLoginDto
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v0/packagingRequest")
    suspend fun getAccessToken(
        @Header("Authorization") accessToken: String = "Bearer $TOKEN",
        ): ResponseLoginDto
}