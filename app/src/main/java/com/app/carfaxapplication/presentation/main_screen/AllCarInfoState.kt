package com.app.carfaxapplication.presentation.main_screen

import com.app.carfaxapplication.domain.model.Listing
import com.app.carfaxapplication.domain.model.SearchArea

data class AllCarInfoState(
    val isLoading: Boolean = false,
    val allCarInfo: List<Listing> = emptyList(),
    val errorMessage: String = "",
    val searchArea: SearchArea = SearchArea()
)
