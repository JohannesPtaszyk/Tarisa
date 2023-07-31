plugins {
    id("dev.pott.compose.convention")
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.voyager.core)
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}

android {
    namespace = "dev.pott.tarisa.architecture.ui"
}
