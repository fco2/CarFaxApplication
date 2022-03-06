package com.app.carfaxapplication.domain.model

data class Listing(
    val dealer: Dealer = Dealer(),
    val dealerType: String = "",
    val id: String = "",
    val images: Images = Images(),
    val make: String = "",
    val model: String = "",
    val trim: String = "",
    val year: Int = 1990,
    val bodyType: String = "",
    val currentPrice: Double = 0.0,
    val driveType: String = "",
    val engine: String = "",
    val exteriorColor: String = "",
    val interiorColor: String = "",
    val mileage: Int = 0,
    val transmission: String = "",
    val vin: String = "",
    val fuel: String = "",
    val displacement: String = ""
)
