package com.app.carfaxapplication.data.remote.dto

import com.app.carfaxapplication.domain.model.Listing
import com.google.gson.annotations.SerializedName

data class ListingDto(
    val advantage: Boolean,
    @SerializedName("backfill")
    val backFill: Boolean,
    val badge: String,
    val bedLength: String,
    val cabType: String,
    val certified: Boolean,
    val dealer: DealerDto,
    val dealerType: String,
    val displacement: String,
    val distanceToDealer: Double,
    val firstSeen: String,
    val followCount: Int,
    val following: Boolean,
    val fuel: String,
    val hasViewed: Boolean,
    val id: String,
    val imageCount: Int,
    val images: ImagesDto,
    val isEnriched: Boolean,
    val listPrice: Double,
    val make: String,
    val model: String,
    //val monthlyPaymentEstimate: MonthlyPaymentEstimateDto,
    val mpgCity: Int,
    val mpgHighway: Int,
    val noAccidents: Boolean,
    val oneOwner: Boolean,
    val onePrice: Double,
    val onlineOnly: Boolean,
    val personalUse: Boolean,
    val recordType: String,
    val sentLead: Boolean,
    val sentLeadAt: String,
    val serviceRecords: Boolean,
    val sortScore: Double,
    val stockNumber: String,
    val subTrim: String,
    val topOptions: List<String>,
    val trim: String,
    val vdpUrl: String,
    val vehicleCondition: String,
    val year: Int,
    @SerializedName("bodytype")
    val bodyType: String,
    val currentPrice: Double,
    @SerializedName("drivetype")
    val driveType: String,
    val engine: String,
    val exteriorColor: String,
    val interiorColor: String,
    val mileage: Int,
    val transmission: String,
    val vin: String
)

fun ListingDto.toListing(): Listing{
    return Listing(
        dealer = dealer.toDealer(),
        dealerType = dealerType,
        id = id,
        images = images.toImages(),
        make = make,
        model = model,
        trim = trim,
        year = year,
        bodyType = bodyType,
        currentPrice = currentPrice,
        driveType = driveType,
        engine = engine,
        exteriorColor = exteriorColor,
        interiorColor = interiorColor,
        mileage = mileage,
        transmission = transmission,
        vin = vin,
        fuel = fuel,
        displacement = displacement
    )
}