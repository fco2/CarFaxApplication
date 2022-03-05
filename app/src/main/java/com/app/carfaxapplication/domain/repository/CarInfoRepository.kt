package com.app.carfaxapplication.domain.repository

import com.app.carfaxapplication.data.remote.dto.CarInfoDto

interface CarInfoRepository {
    suspend fun getAllCarInfo(): CarInfoDto
}