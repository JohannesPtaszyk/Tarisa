import com.android.build.api.dsl.ApplicationExtension
import configurations.configureAndroid
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                defaultConfig.targetSdk = libs.findVersion(ANDROID_TARGET_SDK_ALIAS)
                    .get()
                    .toString()
                    .toInt()
            }
        }
    }

}