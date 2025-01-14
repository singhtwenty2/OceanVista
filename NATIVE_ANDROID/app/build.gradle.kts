plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.singhtwenty2.oceanvista"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.singhtwenty2.oceanvista"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// External Dependencies
dependencies {
    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    // Navigation
    implementation (libs.androidx.navigation.compose)
    // Google Fonts
    implementation (libs.androidx.ui.text.google.fonts)
    // Koin
    implementation (libs.koin.android)
    implementation (libs.koin.androidx.navigation)
    implementation (libs.koin.androidx.compose)
    testImplementation (libs.koin.test.junit4)
    // M3 Extended Icons
    implementation(libs.androidx.material.icons.extended)
    // Location
    implementation(libs.play.services.location)
    // Work Manager
    implementation (libs.androidx.work.runtime.ktx)
    // coil
    implementation(libs.coil.compose)
    // Maps
    implementation (libs.play.services.maps)
    implementation (libs.android.maps.utils)
    implementation(libs.maps.compose)
}