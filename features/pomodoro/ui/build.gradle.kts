plugins {
    id("dev.pott.compose.convention")
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(libs.voyager.core)
                implementation(projects.architecture.ui)
            }
        }
    }
}

android {
    namespace = "dev.pott.tarisa.features.pomodoro.data"
}
