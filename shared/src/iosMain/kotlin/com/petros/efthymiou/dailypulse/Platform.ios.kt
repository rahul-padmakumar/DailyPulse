package com.petros.efthymiou.dailypulse

import platform.UIKit.UIDevice

actual class Platform {
    actual val osName: String
        get() = "iOS"
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model
    actual val deviceDensity: Int
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo(){
        //to implement
    }

}

