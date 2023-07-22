import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("dev.pott.desktop.app.convention")
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
