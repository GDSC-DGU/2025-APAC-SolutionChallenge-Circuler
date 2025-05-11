package com.example.circuler.data.datasource

import com.example.circuler.data.dto.request.RequestPackageDto
import com.example.circuler.data.dto.response.BaseResponse
import com.example.circuler.data.dto.response.ResponsePackageMyDto
import com.example.circuler.data.dto.response.ResponsePackagingListDto
import com.example.circuler.data.dto.response.ResponseRequestPackageDto
import com.example.circuler.data.service.RequestService
import javax.inject.Inject

internal class RequestDataSource @Inject constructor(
    private val requestService: RequestService
) {
    suspend fun getPackagingRequest(): BaseResponse<List<ResponsePackagingListDto>> =
        requestService.getPackagingRequest()

    suspend fun postPackagingRequest(body: RequestPackageDto): BaseResponse<ResponseRequestPackageDto> =
        requestService.postPackagingRequest(body = body)

    suspend fun getPackagingRequestDetail(requestId: Int): BaseResponse<ResponsePackagingListDto> =
        requestService.getPackagingRequestDetail(packagingRequestId = requestId)

    suspend fun getPackagingRequestMy(): List<ResponsePackageMyDto> =
        requestService.getPackagingRequestMy()
}
