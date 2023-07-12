plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.compose.get().pluginId)
    id(libs.plugins.android.application.get().pluginId)
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.shared)
            }
        }
    }
}

android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    namespace = "dev.pott.tarisa"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "dev.pott.focus" // legacy application id from old app name 😪
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    implementation(libs.androidx.app.compat)
}
