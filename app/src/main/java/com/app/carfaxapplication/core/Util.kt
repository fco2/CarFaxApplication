package com.app.carfaxapplication.core

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

object Util {
    fun prettyPrintNum(number: Number): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

    @ExperimentalPermissionsApi
    fun PermissionState.hasBeenDeniedForever(): Boolean {
        return !this.shouldShowRationale && this.permissionRequested
    }
}