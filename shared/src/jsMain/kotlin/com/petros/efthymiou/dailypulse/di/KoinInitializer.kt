package com.petros.efthymiou.dailypulse.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        sharedKoinModules
    )
}.koin