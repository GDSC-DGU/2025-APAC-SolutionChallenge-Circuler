package com.example.circuler.data.datasource

import com.example.circuler.data.dto.response.ResponseLoginDto
import com.example.circuler.data.service.UserService
import javax.inject.Inject

internal class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun getAccessToken(): ResponseLoginDto =
        userService.getAccessToken()
}
