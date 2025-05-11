package com.example.circuler.data.di

import com.example.circuler.data.repositoryimpl.ReqresRepositoryImpl
import com.example.circuler.data.repositoryimpl.RequestRepositoryImpl
import com.example.circuler.data.repositoryimpl.SubmissionRepositoryImpl
import com.example.circuler.data.repositoryimpl.TokenRepositoryImpl
import com.example.circuler.data.repositoryimpl.DeliveryRepositoryImpl
import com.example.circuler.domain.repository.ReqresRepository
import com.example.circuler.domain.repository.RequestRepository
import com.example.circuler.domain.repository.SubmissionRepository
import com.example.circuler.domain.repository.TokenRepository
import com.example.circuler.domain.repository.DeliveryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsReqresRepository(reqresRepositoryImpl: ReqresRepositoryImpl): ReqresRepository

    @Binds
    @Singleton
    fun bindsDeliveryRepository(deliveryRepositoryImpl: DeliveryRepositoryImpl): DeliveryRepository

    @Binds
    @Singleton
    fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    fun bindsRequestRepository(requestRepositoryImpl: RequestRepositoryImpl): RequestRepository

    @Binds
    @Singleton
    fun bindsSubmissionRepository(submissionRepositoryImpl: SubmissionRepositoryImpl): SubmissionRepository

}
