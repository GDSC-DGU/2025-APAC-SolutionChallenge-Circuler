package com.example.circuler.data.di

import com.example.circuler.data.repositoryimpl.ReqresRepositoryImpl
import com.example.circuler.domain.repository.ReqresRepository
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
}
