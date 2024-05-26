plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.alexisdev.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}