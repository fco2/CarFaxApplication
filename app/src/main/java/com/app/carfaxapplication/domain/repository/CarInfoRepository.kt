package com.app.carfaxapplication.domain.repository

import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.domain.model.CarInfo
import kotlinx.coroutines.flow.Flow

interface CarInfoRepository {
    fun getAllCarInfo(): Flow<Resource<CarInfo>>
}