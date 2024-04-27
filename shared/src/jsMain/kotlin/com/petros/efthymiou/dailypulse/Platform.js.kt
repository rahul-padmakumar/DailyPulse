package com.petros.efthymiou.dailypulse

actual class Platform {
    actual val osName: String
        get() = "Web"
    actual val osVersion: String
        get() = "Web"
    actual val deviceModel: String
        get() = "Web"
    actual val density: Int
        get() = 0

    actual fun logSystemInfo() {
        println("($osName, $osVersion, $deviceModel, $density)")
    }
}