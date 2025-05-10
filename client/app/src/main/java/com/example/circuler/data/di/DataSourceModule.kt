package com.example.circuler.data.di

import com.example.circuler.data.datasource.ReqresDataSource
import com.example.circuler.data.datasource.UserDataSource
import com.example.circuler.data.service.ReqresService
import com.example.circuler.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    @Singleton
    fun providesReqresDataSource(
        reqresService: ReqresService
    ): ReqresDataSource = ReqresDataSource(reqresService)

    @Provides
    @Singleton
    fun providesUserDataSource(
        userService: UserService
    ): UserDataSource = UserDataSource(userService)
}
