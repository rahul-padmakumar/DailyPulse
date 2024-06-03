package com.petros.efthymiou.dailypulse

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

    fun create(): SqlDriver
}