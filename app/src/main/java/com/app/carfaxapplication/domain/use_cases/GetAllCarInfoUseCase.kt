package com.app.carfaxapplication.domain.use_cases

import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.domain.model.CarInfo
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCarInfoUseCase @Inject constructor(private val repository: CarInfoRepository) {

    operator fun invoke(): Flow<Resource<CarInfo>>{
        return repository.getAllCarInfo()
    }
}