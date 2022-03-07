package com.app.carfaxapplication.data.local

import androidx.room.*
import com.app.carfaxapplication.data.local.entity.CarInfoEntity

@Dao
interface CarInfoDao {

    //get
    @Query("SELECT * FROM car_info LIMIT 1")
    suspend fun getAllCarInfo(): CarInfoEntity

    //upsert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCarInfo(carInfo: CarInfoEntity)

    //delete
    //since there will ever only be one record, this delete will empty the table
    @Delete
    suspend fun deleteCarInfo(carInfo: CarInfoEntity)

}