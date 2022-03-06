package com.app.carfaxapplication.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.carfaxapplication.core.Resource
import com.app.carfaxapplication.domain.model.SearchArea
import com.app.carfaxapplication.domain.use_cases.GetAllCarInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val allCarInfoUseCase: GetAllCarInfoUseCase): ViewModel(){

    var allCarInfoState by mutableStateOf(AllCarInfoState())
        private set

    var searchArea by mutableStateOf(SearchArea())
        private set

    init {
        getAllCarInfoData()
    }

    private fun getAllCarInfoData(){
        allCarInfoUseCase().onEach {  result ->
            allCarInfoState = when(result){
                is Resource.Loading -> {
                    AllCarInfoState(isLoading = true)
                }
                is Resource.Success -> {
                    searchArea = result.data!!.searchArea
                    AllCarInfoState(isLoading = false, allCarInfo = result.data.listings)
                }
                is Resource.Error -> {
                    AllCarInfoState(isLoading = false, errorMessage = result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}