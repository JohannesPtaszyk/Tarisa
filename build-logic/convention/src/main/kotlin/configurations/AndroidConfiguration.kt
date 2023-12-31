package configurations

import ANDROID_COMPILE_SDK_ALIAS
import ANDROID_MIN_SDK_ALIAS
import COMPOSE_COMPILER_ALIAS
import com.android.build.api.dsl.CommonExtension
import extensions.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import projectJavaVersion

fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    configureAndroid(commonExtension)
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion(COMPOSE_COMPILER_ALIAS).get().toString()
        }
    }
}

fun Project.configureAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
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