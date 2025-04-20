package com.example.circuler.domain.repository

import com.example.circuler.domain.entity.ReqresEntity

interface ReqresRepository {
    suspend fun getReqresLists(page: Int): Result<List<ReqresEntity>>
}
