package com.app.carfaxapplication.presentation.main_screen.components

import android.content.Context
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.carfaxapplication.core.Util
import com.app.carfaxapplication.domain.model.Listing
import com.app.carfaxapplication.domain.model.SearchArea
import com.app.carfaxapplication.ui.theme.DarkBabyBlue
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext


@Composable
fun AllCarInfoItem(
    modifier: Modifier = Modifier,
    listing: Listing,
    searchArea: SearchArea,
    compositionContext: Context,
    onClick: (Listing) -> Unit
){
    Column(modifier = modifier
        .padding(vertical = 6.dp, horizontal = 6.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .padding(8.dp)
        ) {

        AsyncImage(
            model = listing.images.firstPhoto.large, contentDescription = "Car image",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        val yearMakeModelTrim = "${listing.year} ${listing.make} ${listing.model} ${listing.trim}"
        Text(
            text = yearMakeModelTrim,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val priceFormatted = "%,d".format(listing.currentPrice.toInt())
            val price = "$ $priceFormatted"
            Text(
                text = price,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(12.dp))
            Divider(
                modifier = Modifier
                    .height(30.dp)
                    .width(1.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(12.dp))

            val mileage = Util.prettyPrintNum(listing.mileage)
            Text(
                text = "$mileage mi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        val location = "${searchArea.city}, ${searchArea.state}"
        Text(
            text = location,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(1.dp))
        Box(modifier = modifier
            .fillMaxWidth()
                ){
            Text(text = "CALL DEALER", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = DarkBabyBlue, modifier = modifier
                    .padding(15.dp)
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .clickable {
                        //launch pendingIntent to make call
                        val number = "+1${listing.dealer.phone}".toLong()
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.data = Uri.parse("tel:+1$number")
                        startActivity(compositionContext, callIntent, null)
                    }
            )
        }
    }
}

