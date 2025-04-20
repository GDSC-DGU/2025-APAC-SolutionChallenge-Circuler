package com.example.circuler.data.di

import com.example.circuler.data.service.ReqresService
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
}