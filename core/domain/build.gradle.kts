plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}