package com.app.di

import android.content.Context
import androidx.room.Room
import com.app.carfaxapplication.core.Constants
import com.app.carfaxapplication.core.Constants.DATABASE_NAME
import com.app.carfaxapplication.data.local.CarInfoDatabase
import com.app.carfaxapplication.data.remote.api.CarInfoApi
import com.app.carfaxapplication.data.repository.CarInfoRepositoryImpl
import com.app.carfaxapplication.data.util.Converters
import com.app.carfaxapplication.data.util.GsonParser
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import com.google.gson.Gson
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
    fun provideCarInfoDatabase(@ApplicationContext context: Context): CarInfoDatabase{
        return Room.databaseBuilder(
            context,
            CarInfoDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

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
    fun getCarInfoRepository(api: CarInfoApi, database: CarInfoDatabase): CarInfoRepository{
        return CarInfoRepositoryImpl(api, database.carInfoDao)
    }
}