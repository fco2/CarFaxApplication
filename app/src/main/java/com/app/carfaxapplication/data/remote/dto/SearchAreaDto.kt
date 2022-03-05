package com.app.carfaxapplication.data.remote.dto

import com.app.carfaxapplication.domain.model.SearchArea

data class SearchAreaDto(
    val city: String,
    val dynamicRadii: List<Double>,
    val dynamicRadius: Boolean,
    val latitude: Double,
    val longitude: Double,
    val radius: Double,
    val state: String,
    val zip: String
)

fun SearchAreaDto.toSearchArea(): SearchArea{
    return SearchArea(
        city = city,
        state = state,
        zip = zip
    )
}