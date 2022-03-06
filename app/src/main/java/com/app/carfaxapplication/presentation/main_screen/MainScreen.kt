package com.app.carfaxapplication.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.app.carfaxapplication.ui.theme.CustomBackground
import com.app.carfaxapplication.ui.theme.DarkBabyBlue

@Composable
fun MainScreen(
    navController: NavController,
    carfaxTitle: String,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val compositionContext = LocalContext.current
    val allCarInfoState = viewModel.allCarInfoState
    val searchArea = viewModel.searchArea

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
                AllCarInfoItem(listing = listingItem, searchArea = searchArea, compositionContext = compositionContext){
                    // navigate to detail view
                }
            }
        }
    }
}