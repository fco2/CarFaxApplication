package com.app.carfaxapplication.presentation.details_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.carfaxapplication.core.Util
import com.app.carfaxapplication.ui.theme.DarkBabyBlue
import com.app.carfaxapplication.ui.theme.LightGrey
import com.app.carfaxapplication.ui.theme.NotSoBlack

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    vehicleInfoText: String = "",
    compositionContext: Context,
){
    val state = viewModel.currentCarInfoState

    Column(modifier = modifier
        .padding(vertical = 6.dp, horizontal = 6.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .padding(8.dp)
    ) {

        AsyncImage(
            model = state.listing.images.firstPhoto.large, contentDescription = "Car image",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        val yearMakeModelTrim =
            "${state.listing.year} ${state.listing.make} ${state.listing.model} ${state.listing.trim}"
        Text(
            text = yearMakeModelTrim,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = NotSoBlack,
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val priceFormatted = "%,d".format(state.listing.currentPrice.toInt())
            val price = "$ $priceFormatted"
            Text(
                text = price,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(12.dp))
            Divider(
                modifier = Modifier
                    .height(32.dp)
                    .width(2.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(12.dp))

            val mileage = Util.prettyPrintNum(state.listing.mileage)
            Text(
                text = "$mileage mi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .height(1.dp)
        )
        Text(
            text = vehicleInfoText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        //create columns for the info part
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Location Info Row
                Text(
                    text = "Location",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = viewModel.location,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Exterior Color Info Row
                Text(
                    text = "Exterior Color",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = state.listing.exteriorColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Interior Color Info Row
                Text(
                    text = "Interior Color",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = state.listing.interiorColor,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Drive Type Info Row
                Text(
                    text = "Drive Type",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp)
                )
                Text(
                    text = state.listing.driveType,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Transmission Info Row
                Text(
                    text = "Transmission",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp)
                )
                Text(
                    text = state.listing.transmission,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Body Style Info Row
                Text(
                    text = "Body Style",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp)
                )
                Text(
                    text = state.listing.bodyType,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Engine Info Row
                Text(
                    text = "Engine",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp)
                )
                val engineAndDisplacement = "${state.listing.engine} ${state.listing.displacement}"
                Text(
                    text = engineAndDisplacement,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Fuel Info Row
                Text(
                    text = "Fuel",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightGrey,
                    modifier = modifier.padding(start = 10.dp)
                )
                Text(
                    text = state.listing.fuel,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .height(1.dp)
        )

        Box(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(DarkBabyBlue)
                .clickable {
                    //launch pendingIntent to make call
                    val number = "+1${state.listing.dealer.phone}".toLong()
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:+1$number")
                    ContextCompat.startActivity(compositionContext, callIntent, null)
                }
        ) {
            Text(text = "CALL DEALER", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = Color.White, modifier = modifier
                    .padding(15.dp)
                    .align(Alignment.Center)

            )
        }

    }
}