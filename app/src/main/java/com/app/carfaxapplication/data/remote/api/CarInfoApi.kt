package com.app.carfaxapplication.data.remote.api

import com.app.carfaxapplication.data.remote.dto.CarInfoDto
import retrofit2.http.GET

interface CarInfoApi {

    @GET("/assignment.json")
    suspend fun getAllCarInfo(): CarInfoDto

}