package com.petros.efthymiou.dailypulse

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory{
    actual fun create():SqlDriver = NativeSqliteDriver(
        schema = DailyPulseDatabase.Schema,
        name = "DailyPulse.database.db"
    )
}