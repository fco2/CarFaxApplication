package com.app.carfaxapplication.domain.use_cases

import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.data.remote.dto.toCarInfo
import com.app.carfaxapplication.domain.model.CarInfo
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCarInfoUseCase @Inject constructor(private val repository: CarInfoRepository) {

    operator fun invoke(): Flow<Resource<CarInfo>> = flow {
        try {
            emit(Resource.Loading<CarInfo>())
            val allCarInfo = repository.getAllCarInfo().toCarInfo()
            emit(Resource.Success(allCarInfo))
        }catch (e: HttpException){
            emit(Resource.Error<CarInfo>(e.localizedMessage ?: "An unexpected error occurred!"))
        }catch (e: IOException){
            emit(Resource.Error<CarInfo>(e.localizedMessage ?: "An unexpected error occurred!"))
        }
    }
}