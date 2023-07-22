package configurations

import ANDROID_COMPILE_SDK_ALIAS
import ANDROID_MIN_SDK_ALIAS
import COMPOSE_COMPILER_ALIAS
import com.android.build.api.dsl.CommonExtension
import extensions.libs
import projectJavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.jvm.internal.Intrinsics.Kotlin

fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>
) {
    configureAndroid(commonExtension)
    commonExtension.apply {
        @Suppress("UnstableApiUsage") buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion(COMPOSE_COMPILER_ALIAS).get().toString()
        }
    }
}

fun Project.configureAndroid(commonExtension: CommonExtension<*, *, *, *>) {
    commonExtension.apply {
        compileSdk = libs.findVersion(ANDROID_COMPILE_SDK_ALIAS).get().toString().toInt()
        defaultConfig {
            minSdk = libs.findVersion(ANDROID_MIN_SDK_ALIAS).get().toString().toInt()
        }

        compileOptions {
            sourceCompatibility = projectJavaVersion
            targetCompatibility = projectJavaVersion
            isCoreLibraryDesugaringEnabled = true
        }

        configureKotlinAndroid()

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
        }
    }
}