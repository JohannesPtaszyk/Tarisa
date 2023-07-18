import com.android.build.gradle.LibraryExtension
import extensions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper

@Suppress("unused")
class ComposeMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {

                @Suppress("UnstableApiUsage") buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("composeCompiler").get().toString()
                }

                compileSdk = libs.findVersion("androidCompileSdk").get().toString().toInt()
                defaultConfig {
                    minSdk = libs.findVersion("androidMinSdk").get().toString().toInt()
                    targetSdk = libs.findVersion("androidTargetSdk").get().toString().toInt()
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            extensions.configure<KotlinMultiplatformExtension> {
                jvmToolchain(17)

                android()

                jvm("desktop")

                iosX64()
                iosArm64()
                iosSimulatorArm64()

                @Suppress("UNUSED_VARIABLE") sourceSets {
                    val commonMain by getting
                    val androidMain by getting
                    val iosX64Main by getting
                    val iosArm64Main by getting
                    val iosSimulatorArm64Main by getting
                    val iosMain by creating {
                        dependsOn(commonMain)
                        iosX64Main.dependsOn(this)
                        iosArm64Main.dependsOn(this)
                        iosSimulatorArm64Main.dependsOn(this)
                    }
                    val desktopMain by getting
                }
            }
        }
    }
}