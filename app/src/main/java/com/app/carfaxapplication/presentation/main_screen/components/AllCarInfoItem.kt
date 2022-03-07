package com.app.carfaxapplication.presentation.main_screen.components

import android.Manifest
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.carfaxapplication.core.Util
import com.app.carfaxapplication.core.Util.hasBeenDeniedForever
import com.app.carfaxapplication.domain.model.Listing
import com.app.carfaxapplication.domain.model.SearchArea
import com.app.carfaxapplication.ui.theme.DarkBabyBlue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState


@ExperimentalPermissionsApi
@Composable
fun AllCarInfoItem(
    modifier: Modifier = Modifier,
    listing: Listing,
    searchArea: SearchArea,
    placeHolderImageId: Int,
    imageContentDescription: String,
    onClick: (Listing) -> Unit
){
    //get the context here
    val context = LocalContext.current
    //get the permissionState
    val permissionState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

    Column(modifier = modifier
        .padding(vertical = 6.dp, horizontal = 6.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .padding(8.dp)
        .clickable { onClick(listing) }
        ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(listing.images.firstPhoto.large)
                .crossfade(true)
                .error(placeHolderImageId)
                .build(),
            placeholder = painterResource(placeHolderImageId),
            contentDescription = imageContentDescription,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
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
                        val number = listing.dealer.phone.toLong()
                        val callIntent = Intent(Intent.ACTION_DIAL) //ACTION_CALL calls directly but ACTION_DIAL gives user option whether to call or not
                        callIntent.data = Uri.parse("tel:$number")
                        when {
                            permissionState.hasPermission -> {
                                startActivity(context, callIntent, null)
                            }
                            permissionState.hasBeenDeniedForever() -> {
                                //do something
                            }
                            else -> {
                                permissionState.launchPermissionRequest()
                            }
                        }
                    }
            )
        }
    }
}

















