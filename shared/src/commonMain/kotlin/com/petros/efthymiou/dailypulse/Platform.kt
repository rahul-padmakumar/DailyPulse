package com.petros.efthymiou.dailypulse

expect class Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val deviceDensity: Int

    fun logSystemInfo()
}
