package com.petros.efthymiou.dailypulse

interface ErrorState {
    val status: Int
    val errorMessage: String?
    val errorTitle: String?
}