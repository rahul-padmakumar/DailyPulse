package com.petros.efthymiou.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log

actual class Platform{
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = Build.VERSION.SDK_INT.toString()
    actual val deviceModel: String
        get() = Build.MANUFACTURER
    actual val deviceDensity: Int
        get() = Resources.getSystem().displayMetrics.density.toInt()

    actual fun logSystemInfo() {
        Log.d(
            "ANDROID_TAG",
            "Device Info: $osName $osVersion $deviceModel $deviceDensity"
        )
    }

}