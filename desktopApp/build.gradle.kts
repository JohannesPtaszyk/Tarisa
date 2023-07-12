import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.compose.get().pluginId)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(projects.shared)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Tarisa"
            packageVersion = "1.0.0"
        }
    }
}
