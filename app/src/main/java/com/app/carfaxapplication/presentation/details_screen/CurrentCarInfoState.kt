package com.app.carfaxapplication.presentation.details_screen

import com.app.carfaxapplication.domain.model.Listing

data class CurrentCarInfoState(
    val listing: Listing = Listing(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
