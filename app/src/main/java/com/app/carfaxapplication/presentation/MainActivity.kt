package com.app.carfaxapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.carfaxapplication.R
import com.app.carfaxapplication.presentation.main_screen.MainScreen
import com.app.carfaxapplication.presentation.util.ScreenRoutes
import com.app.carfaxapplication.ui.theme.CarFaxApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarFaxApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    val navController = rememberNavController()

                    val carfaxTitle = getString(R.string.carFax_title)

                    NavHost(navController = navController, startDestination = ScreenRoutes.MAIN_SCREEN){
                        composable(route = ScreenRoutes.MAIN_SCREEN){
                            MainScreen(navController = navController, carfaxTitle)
                        }
                        //composable(route = ScreenRoutes.DETAIL_SCREEN)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarFaxApplicationTheme {
        Greeting("Android")
    }
}