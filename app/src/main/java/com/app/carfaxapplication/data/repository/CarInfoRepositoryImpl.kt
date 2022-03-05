package com.app.carfaxapplication.data.repository

import com.app.carfaxapplication.data.remote.api.CarInfoApi
import com.app.carfaxapplication.data.remote.dto.CarInfoDto
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import javax.inject.Inject

class CarInfoRepositoryImpl @Inject constructor(private val api: CarInfoApi): CarInfoRepository {
    override suspend fun getAllCarInfo(): CarInfoDto {
        return api.getAllCarInfo()
    }
}