package com.app.carfaxapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.carfaxapplication.domain.model.CarInfo
import com.app.carfaxapplication.domain.model.Listing
import com.app.carfaxapplication.domain.model.SearchArea

@Entity(
    tableName = "car_info"
)
data class CarInfoEntity(
    val listings: List<Listing>,
    val searchArea: SearchArea,
    @PrimaryKey val id: Long? = null
)

fun CarInfoEntity.toCarInfo(): CarInfo{
    return CarInfo(
        listings = listings,
        searchArea = searchArea
    )
}
