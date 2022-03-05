package com.app.carfaxapplication.data.remote.dto

data class DealerDto(
    val address: String,
    val carfaxId: String,
    val city: String,
    val dealerAverageRating: Double,
    val dealerInventoryUrl: String,
    val dealerReviewCount: Int,
    val dealerReviewDate: String,
    val dealerReviewRating: Int,
    val dealerReviewReviewer: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val onlineOnly: Boolean,
    val state: String,
    val zip: String,
    val phone: String
)