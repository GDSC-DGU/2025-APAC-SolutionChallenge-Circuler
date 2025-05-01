package com.example.circuler.data.datasource

import com.example.circuler.data.dto.response.ResponseReqresDto
import com.example.circuler.data.service.ReqresService
import javax.inject.Inject

internal class ReqresDataSource @Inject constructor(
    private val reqresService: ReqresService
) {
    suspend fun getReqresLists(page: Int): ResponseReqresDto =
        reqresService.getReqresLists(page)
}
