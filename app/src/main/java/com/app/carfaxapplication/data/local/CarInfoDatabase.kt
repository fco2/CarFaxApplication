package com.app.carfaxapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.carfaxapplication.data.local.entity.CarInfoEntity
import com.app.carfaxapplication.data.util.Converters

@Database(
    entities = [CarInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CarInfoDatabase: RoomDatabase() {
    abstract val carInfoDao: CarInfoDao
}