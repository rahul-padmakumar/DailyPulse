package com.petros.efthymiou.dailypulse

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory(private val context: Context){
    actual fun create(): SqlDriver = AndroidSqliteDriver(
        schema = DailyPulseDatabase.Schema,
        context = context,
        name = "DailyPulse.database.db"
    )
}