plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.8.0"
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.sqdelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    task("testClasses")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.koin.core)
                implementation(libs.coroutines.extensions)
            }
        }
        val androidMain by getting{
            dependencies{
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.android.driver)
            }
        }
        val iosMain by getting{
            dependencies{
                implementation(libs.ktor.client.darwin)
                implementation(libs.native.driver)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.petros.efthymiou.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

sqldelight{
    databases{
        create("DailyPulseDatabase"){
            packageName.set("petros.efthymiou.dailypulse.db")
        }
    }
}
