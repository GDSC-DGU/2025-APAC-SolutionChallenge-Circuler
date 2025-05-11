package com.example.circuler.data.di

import android.content.SharedPreferences
import com.example.circuler.data.datalocal.datasource.TokenDataSource
import com.example.circuler.data.datalocal.datasourceimpl.TokenDataSourceImpl
import com.example.circuler.data.datasource.ReqresDataSource
import com.example.circuler.data.datasource.RequestDataSource
import com.example.circuler.data.datasource.SubmissionDataSource
import com.example.circuler.data.datasource.DeliveryDataSource
import com.example.circuler.data.service.ReqresService
import com.example.circuler.data.service.RequestService
import com.example.circuler.data.service.SubmissionService
import com.example.circuler.data.service.DeliveryService
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
    fun providesDeliveryDataSource(
        deliveryService: DeliveryService
    ): DeliveryDataSource = DeliveryDataSource(deliveryService)

    @Provides
    @Singleton
    fun provideTokenDataStore(sharedPreferences: SharedPreferences): TokenDataSource {
        return TokenDataSourceImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesRequestDataSource(
        requestService: RequestService
    ): RequestDataSource = RequestDataSource(requestService)

    @Provides
    @Singleton
    fun providesSubmissionDataSource(
        submissionService: SubmissionService
    ): SubmissionDataSource = SubmissionDataSource(submissionService)
}
