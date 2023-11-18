import org.gradle.api.JavaVersion

object Configuration {
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 26
    const val versionCode = 1
    const val versionName = "1.0"
    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "11"
}