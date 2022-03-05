package com.app.carfaxapplication.domain.model

data class CarInfo(
    val listings: List<Listing>,
    val searchArea: SearchArea,
)
