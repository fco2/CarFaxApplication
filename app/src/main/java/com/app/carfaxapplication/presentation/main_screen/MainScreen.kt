package com.app.carfaxapplication.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.carfaxapplication.presentation.main_screen.components.AllCarInfoItem
import com.app.carfaxapplication.presentation.util.ScreenRoutes
import com.app.carfaxapplication.ui.theme.CustomBackground
import com.app.carfaxapplication.ui.theme.DarkBabyBlue
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    carfaxTitle: String,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val allCarInfoState = viewModel.allCarInfoState
    val searchArea = viewModel.searchArea

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //The main screen
        Column(modifier = Modifier
            .fillMaxSize()
            .background(CustomBackground)){

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(DarkBabyBlue)
                .padding(start = 30.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){

                Text(text = carfaxTitle, fontSize = 26.sp, fontWeight = FontWeight.SemiBold, color = CustomBackground)
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(allCarInfoState.allCarInfo){ listingItem ->
                    AllCarInfoItem(listing = listingItem, searchArea = searchArea){ listing ->
                        // navigate to detail view
                        val location = "${searchArea.city}, ${searchArea.state}"
                        navController.navigate(ScreenRoutes.DETAIL_SCREEN
                                + "?make=${listing.make}&model=${listing.model}&year=${listing.year}&price=${listing.currentPrice}&location=${location}")
                    }
                }
            }
        }
        // progress indicator
        if(allCarInfoState.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        // error message
        if(allCarInfoState.errorMessage.isNotBlank()){
            Text(text = allCarInfoState.errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.Center))
        }
    }
}