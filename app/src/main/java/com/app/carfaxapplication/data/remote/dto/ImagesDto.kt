package com.app.carfaxapplication.data.remote.dto

data class ImagesDto(
    val baseUrl: String,
    val large: List<String>,
    val medium: List<String>,
    val small: List<String>,
    val firstPhoto: FirstPhotoDto
)