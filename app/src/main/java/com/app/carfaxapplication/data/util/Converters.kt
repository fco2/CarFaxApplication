package com.app.carfaxapplication.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.carfaxapplication.domain.model.Listing
import com.app.carfaxapplication.domain.model.SearchArea
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {
    //type converter for List<Listing>
    @TypeConverter
    fun toJsonObjectFromListings(listings: List<Listing>): String?{
        return jsonParser.toJsonFromObject(
            listings,
            object : TypeToken<List<Listing>>(){}.type
        )
    }

    @TypeConverter
    fun fromJsonObjectToListings(jsonString: String): List<Listing>{
       return  jsonParser.fromJsonToObject(
            jsonString,
            object : TypeToken<List<Listing>>(){}.type
        )
    }

    //Type converter for SearchArea
    @TypeConverter
    fun toJsonObjectFromSearchArea(searchArea: SearchArea): String?{
        return jsonParser.toJsonFromObject(
            searchArea,
            object : TypeToken<SearchArea>(){}.type
        )
    }

    @TypeConverter
    fun fromJsonObjectToSearchArea(jsonString: String): SearchArea{
        return jsonParser.fromJsonToObject(
            jsonString,
            object: TypeToken<SearchArea>(){}.type
        )
    }
}














