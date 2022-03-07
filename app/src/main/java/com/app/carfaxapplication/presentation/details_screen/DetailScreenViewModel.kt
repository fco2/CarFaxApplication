package com.app.carfaxapplication.presentation.details_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.domain.use_cases.GetAllCarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val allCarInfoUseCase: GetAllCarInfoUseCase,
    savedStateHandle: SavedStateHandle
    ): ViewModel() {

    var currentCarInfoState by mutableStateOf(CurrentCarInfoState())
        private set

    private var make: String = ""
    private var model: String = ""
    private var year: Int = -1
    private var price: Double = 0.0

    var location by mutableStateOf("")
        private set

    init{
        make = savedStateHandle.get<String>("make")!!
        model = savedStateHandle.get<String>("model")!!
        year = savedStateHandle.get<Int>("year")!!
        price = savedStateHandle.get<Float>("price")!!.toDouble()
        location = savedStateHandle.get<String>("location")!!

        getCurrentCarInfo(make, model, year, price)
    }

    private fun getCurrentCarInfo(make: String, model: String, year: Int, price: Double){
        allCarInfoUseCase().onEach {  result ->
            currentCarInfoState = when(result){
                is Resource.Loading -> {
                    CurrentCarInfoState(isLoading = true)
                }
                is Resource.Success -> {
                    // this will give us the listing where below filters matches the properties returned from the list of listings
                    //filters include: make, model, year and price . Using these since the id was not unique.

                    CurrentCarInfoState(isLoading = false, listing = result.data!!.listings
                        .first {it.make == make && it.model == model && it.year == year && it.currentPrice == price})
                }
                is Resource.Error -> {
                    CurrentCarInfoState(isLoading = false, errorMessage = result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}