package com.app.carfaxapplication.data.remote.dto

import com.app.carfaxapplication.domain.model.Images

data class ImagesDto(
    val baseUrl: String,
    val large: List<String>,
    val medium: List<String>,
    val small: List<String>,
    val firstPhoto: FirstPhotoDto
)

fun ImagesDto.toImages(): Images{
    return Images(
        firstPhoto = firstPhoto.toFirstPhoto()
    )
}