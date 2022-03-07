package com.app.carfaxapplication.data.repository

import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.data.local.CarInfoDao
import com.app.carfaxapplication.data.local.entity.CarInfoEntity
import com.app.carfaxapplication.data.local.entity.toCarInfo
import com.app.carfaxapplication.data.remote.api.CarInfoApi
import com.app.carfaxapplication.data.remote.dto.toCarInfoEntity
import com.app.carfaxapplication.domain.model.CarInfo
import com.app.carfaxapplication.domain.repository.CarInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CarInfoRepositoryImpl @Inject constructor(
    private val api: CarInfoApi,
    private val dao: CarInfoDao
): CarInfoRepository {

    override fun getAllCarInfo(): Flow<Resource<CarInfo>> = flow{
        emit(Resource.Loading<CarInfo>())
        //this null check is important, since
        val allCarInfoFromDb = dao.getAllCarInfo()?.toCarInfo()

        emit(Resource.Loading<CarInfo>(allCarInfoFromDb))
        lateinit var allCarInfoFromApi: CarInfoEntity
        try {
            allCarInfoFromApi = api.getAllCarInfo().toCarInfoEntity()
            //this will ensure single source of truth
            dao.deleteCarInfo(allCarInfoFromApi)
            dao.upsertCarInfo(allCarInfoFromApi)
        }catch (e: HttpException){
            emit(Resource.Error<CarInfo>(e.localizedMessage ?: "An unexpected error occurred!", data = allCarInfoFromDb))
        }catch (e: IOException){
            emit(Resource.Error<CarInfo>(e.localizedMessage ?: "An unexpected error occurred!", data = allCarInfoFromDb))
        }finally {
            val latestCarInfoFromDb = dao.getAllCarInfo().toCarInfo()
            emit(Resource.Success<CarInfo>(latestCarInfoFromDb))
        }
    }
}