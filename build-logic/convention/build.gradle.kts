plugins {
    `kotlin-dsl`
}

group = "dev.pott.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.kotlin.multiplatform.plugin)
    compileOnly(libs.kotlin.jvm.plugin)
    compileOnly(libs.kotlin.android.plugin)
    compileOnly(libs.kotlin.cocoapods.plugin)
    compileOnly(libs.compose.plugin)
    compileOnly(libs.ksp.plugin)
    compileOnly(libs.android.plugin)
}

gradlePlugin {
    plugins {
        register("ComposeMultiplatformLibraryConventionPlugin") {
            id = "dev.pott.compose.convention"
            implementationClass = "ComposeMultiplatformLibraryConventionPlugin"
        }
    }
}