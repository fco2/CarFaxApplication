package com.app.carfaxapplication.data.remote.dto

import com.app.carfaxapplication.data.local.entity.CarInfoEntity

data class CarInfoDto(
    val backfillCount: Int,
    val dealerNewCount: Int,
    val dealerUsedCount: Int,
    val enhancedCount: Int,
    val listings: List<ListingDto>,
    val page: Int,
    val pageSize: Int,
    val searchArea: SearchAreaDto,
    val totalListingCount: Int,
    val totalPageCount: Int
)

fun CarInfoDto.toCarInfoEntity(): CarInfoEntity{
    return CarInfoEntity(
        listings = listings.map { it.toListing() },
        searchArea = searchArea.toSearchArea()
    )
}