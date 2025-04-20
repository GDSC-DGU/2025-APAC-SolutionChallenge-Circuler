package com.example.circuler.data.service

import com.example.circuler.data.dto.response.ResponseReqresDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("api/users")
    suspend fun getReqresLists(
        @Query("page") page: Int
    ): ResponseReqresDto
}