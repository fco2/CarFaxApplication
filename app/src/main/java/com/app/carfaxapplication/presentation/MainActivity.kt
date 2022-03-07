package com.app.carfaxapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.carfaxapplication.R
import com.app.carfaxapplication.presentation.details_screen.DetailScreen
import com.app.carfaxapplication.presentation.main_screen.MainScreen
import com.app.carfaxapplication.presentation.util.ScreenRoutes
import com.app.carfaxapplication.ui.theme.CarFaxApplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarFaxApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val carfaxTitle = getString(R.string.carFax_title)
                    val vehicleInfoText = getString(R.string.vehicle_info)
                    val placeholderImgId = R.drawable.placeholder_img
                    val imgContentDescription = getString(R.string.car_image_placeholder_text)

                    NavHost(navController = navController, startDestination = ScreenRoutes.MAIN_SCREEN){
                        composable(route = ScreenRoutes.MAIN_SCREEN){
                            MainScreen(
                                navController = navController,
                                carfaxTitle,
                                placeholderImgId,
                                imgContentDescription
                            )
                        }
                        composable(
                            route = ScreenRoutes.DETAIL_SCREEN + "?make={make}&model={model}&year={year}&price={price}&location={location}",
                            arguments =listOf(
                                navArgument(name= "make"){
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(name = "model"){
                                    type = NavType.StringType
                                    defaultValue = "-1"
                                },
                                navArgument(name = "year"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(name = "price"){
                                    type = NavType.FloatType
                                    defaultValue = 0.0
                                },
                                navArgument(name = "location"){
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ){
                            DetailScreen(
                                vehicleInfoText = vehicleInfoText,
                                placeHolderImageId = placeholderImgId,
                                imageContentDescription = imgContentDescription
                            )
                        }
                    }
                }
            }
        }
    }
}