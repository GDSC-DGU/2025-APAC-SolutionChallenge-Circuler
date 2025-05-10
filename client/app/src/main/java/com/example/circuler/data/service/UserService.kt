package com.example.circuler.data.service

import com.example.circuler.data.dto.response.ResponseLoginDto
import retrofit2.http.GET

interface UserService {
    @GET("/api/v0/packagingRequest")
    suspend fun getAccessToken(): ResponseLoginDto
}