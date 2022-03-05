package com.app.carfaxapplication.data.remote.dto

data class CarInfoDto(
    val backfillCount: Int,
    val dealerNewCount: Int,
    val dealerUsedCount: Int,
    val enhancedCount: Int,
    val listings: List<ListingsDto>,
    val page: Int,
    val pageSize: Int,
    val searchArea: SearchAreaDto,
    val totalListingCount: Int,
    val totalPageCount: Int
)