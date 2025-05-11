package com.example.circuler.data.di

import com.example.circuler.data.service.ReqresService
import com.example.circuler.data.service.RequestService
import com.example.circuler.data.service.SubmissionService
import com.example.circuler.data.service.DeliveryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideReqresService(retrofit: Retrofit): ReqresService =
        retrofit.create(ReqresService::class.java)

    @Provides
    @Singleton
    fun provideDeliveryService(retrofit: Retrofit): DeliveryService =
        retrofit.create(DeliveryService::class.java)

    @Provides
    @Singleton
    fun provideRequestService(retrofit: Retrofit): RequestService =
        retrofit.create(RequestService::class.java)

    @Provides
    @Singleton
    fun provideSubmissionService(retrofit: Retrofit): SubmissionService =
        retrofit.create(SubmissionService::class.java)
}
