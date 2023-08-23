plugins {
    id("dev.pott.compose.convention")
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material) // iOS workaround
                implementation(compose.materialIconsExtended)
                implementation(compose.material3)
            }
        }
    }
}

android {
    namespace = "dev.pott.tarisa.design.components"
}
