import com.android.build.api.dsl.LibraryExtension
import configurations.configureAndroidCompose
import configurations.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class ComposeMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
                apply("com.android.library")
            }
            val libraryExtension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(libraryExtension)
            configureKotlinMultiplatform()
        }
    }
}