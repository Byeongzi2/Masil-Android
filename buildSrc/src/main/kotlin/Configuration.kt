import org.gradle.api.JavaVersion

object Configuration {
    const val compileSdk = 33
    const val targetSdk = 33
    const val minSdk = 24
    const val versionCode = 1
    const val versionName = "1.0"
    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "11"
}