package com.app.di

import com.app.carfaxapplication.core.Constants
import com.app.carfaxapplication.data.remote.api.CarInfoApi
import com.app.carfaxapplication.data.repository.CarInfoRepositoryImpl
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarFaxAppModule {

    @Singleton
    @Provides
    fun getCarInfoApi(): CarInfoApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun getCarInfoRepository(api: CarInfoApi): CarInfoRepository{
        return CarInfoRepositoryImpl(api)
    }
}