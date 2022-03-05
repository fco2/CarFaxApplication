package com.app.carfaxapplication.data.remote.dto

import com.app.carfaxapplication.domain.model.FirstPhoto

data class FirstPhotoDto(
    val medium: String,
    val small: String,
    val large: String
)

fun FirstPhotoDto.toFirstPhoto(): FirstPhoto{
    return FirstPhoto(
        large = large
    )
}