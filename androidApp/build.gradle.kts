plugins {
    id("dev.pott.android.app.convention")
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
    namespace = "dev.pott.tarisa"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "dev.pott.focus" // legacy application id from old app name 😪
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.app.compat)
}