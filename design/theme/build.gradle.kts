plugins {
    id("dev.pott.compose.convention")
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material) // Somehow needed to build iOS, could not figure out why so far
                implementation(compose.materialIconsExtended)
                implementation(compose.material3)
            }
        }
    }
}

android {
    namespace = "dev.pott.tarisa.design"
}
