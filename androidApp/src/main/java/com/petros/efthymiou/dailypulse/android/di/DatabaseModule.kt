package com.petros.efthymiou.dailypulse.android.di

import com.petros.efthymiou.dailypulse.DatabaseDriverFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

val databaseModule = module {
    single {
        DatabaseDriverFactory(androidContext()).create()
    }
    single {
        DailyPulseDatabase(get())
    }
}