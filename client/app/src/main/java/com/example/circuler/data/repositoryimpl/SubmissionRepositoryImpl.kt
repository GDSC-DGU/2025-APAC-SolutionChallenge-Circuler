package com.example.circuler.data.repositoryimpl

import android.content.Context
import com.example.circuler.data.datasource.SubmissionDataSource
import com.example.circuler.data.dto.request.toDto
import com.example.circuler.domain.entity.AcceptEntity
import com.example.circuler.domain.entity.PackageImageEntity
import com.example.circuler.domain.entity.PackageListCardWithMethodEntity
import com.example.circuler.domain.entity.SubmissionPackagingEntity
import com.example.circuler.domain.repository.SubmissionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import javax.inject.Inject

internal class SubmissionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val submissionDataSource: SubmissionDataSource
) : SubmissionRepository {
    override suspend fun postPackageRequest(requestId: Int, submissionData: SubmissionPackagingEntity): Result<Unit> =
        runCatching {
            submissionDataSource.postPackageRequest(
                requestId = requestId,
                body = submissionData.toDto()
            )
        }

    override suspend fun postPackagingDelivery(requestId: Int): Result<Unit> =
        runCatching {
            submissionDataSource.postPackagingDelivery(
                requestId = requestId
            )
        }

    override suspend fun getSubmittedData(requestId: Int): Result<List<PackageListCardWithMethodEntity>> =
        runCatching {
            submissionDataSource.getSubmittedData(
                requestId = requestId
            ).result.map {
                it.toEntity()
            }
        }

    override suspend fun getPackageAccept(requestId: Int, submissionId: Int): Result<AcceptEntity> =
        runCatching {
            submissionDataSource.getPackageAccept(
                requestId = requestId,
                submissionId = submissionId
            ).result.toEntity()
        }

    override suspend fun postPackageImage(submissionId: Int, file: File?): Result<PackageImageEntity> {
        return runCatching {
            submissionDataSource.postPackageImage(
                submissionId = submissionId,
                file = if (file == null) {
                    file
                } else {
                    contentFileRequestBody(file = file)
                }
            ).result.toEntity()
        }
    }
}

fun contentFileRequestBody(file: File) : MultipartBody.Part {
    val contentType = "image/png".toMediaTypeOrNull()
    val requestBody = file.asRequestBody(contentType)
    val multipart = MultipartBody.Part.createFormData("file", file.name, requestBody)
    Timber.d("contentFileRequestBody 실행 : ${file.name}, $requestBody, ${file.length()}")
    return multipart
}
