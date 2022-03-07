package com.app.carfaxapplication.data.util

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJsonToObject(json: String, type: Type): T

    fun <T> toJsonFromObject(obj: T, type: Type): String?
}