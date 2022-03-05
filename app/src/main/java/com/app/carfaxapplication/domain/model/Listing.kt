package com.app.carfaxapplication.domain.model

import com.app.carfaxapplication.data.remote.dto.DealerDto
import com.app.carfaxapplication.data.remote.dto.ImagesDto
import com.google.gson.annotations.SerializedName

data class Listing(
    val dealer: Dealer,
    val dealerType: String,
    val id: String,
    val images: Images,
    val make: String,
    val model: String,
    val trim: String,
    val year: Int,
    val bodyType: String,
    val currentPrice: Double,
    val driveType: String,
    val engine: String,
    val exteriorColor: String,
    val interiorColor: String,
    val mileage: Int,
    val transmission: String,
    val vin: String
)
