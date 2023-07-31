plugins {
    id(libs.plugins.cocoapods.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id("dev.pott.compose.convention")
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "Tarisa App"
        homepage = "https://pott.dev"
        ios.deploymentTarget = libs.versions.iosDevelopmentTarget.get()
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "dev.pott.tarisa.shared")
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.kotlin.inject.runtime)
                implementation(libs.voyager.bottomsheet.navigator)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.navigator.tab)
                implementation(libs.voyager.transitions)
                implementation(libs.material3.window.size)
                implementation(projects.design.theme)
                implementation(projects.features.pomodoro.ui)
                implementation(projects.architecture.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.app.compat)
                api(libs.androidx.core.ktx)
                api(libs.kotlin.coroutines.android)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.kotlin.coroutines.swing)
            }
        }
    }
}

dependencies {
    listOf(
        "kspDesktop",
        "kspAndroid",
        "kspIosX64",
        "kspIosArm64",
        "kspIosSimulatorArm64",
    ).forEach { ksp ->
        add(ksp, libs.kotlin.inject.compiler)
    }
}


android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    namespace = "dev.pott.tarisa"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}
