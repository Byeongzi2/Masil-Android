plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.posomo.masil"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.posomo.masil"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "MAP_CLIENT_ID", "\"9teby2rpq2\"")
            isDebuggable = true
        }
        getByName("release") {
            //TODO : check release client id
            buildConfigField("String", "MAP_CLIENT_ID", "\"9teby2rpq2\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Configuration.javaVersion
        targetCompatibility = Configuration.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        buildConfig = true
        dataBinding = true
    }
}

dependencies {

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)
    implementation(Dependencies.androidx.splashScreen)

    implementation(Dependencies.navigation.ui)
    implementation(Dependencies.navigation.fragment)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    implementation(Dependencies.jsonParser.gsonConverter)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttp.loggingInterceptor)

    implementation(Dependencies.kotlin.coroutine)

    implementation(Dependencies.DataStore.preferences)

    implementation(Dependencies.ViewModels.activity)
    implementation(Dependencies.ViewModels.fragment)

    implementation(Dependencies.imageLoader.glide)
    kapt(Dependencies.imageLoader.glideCompiler)

    implementation(Dependencies.naver.map)
    api(Dependencies.naver.gmsLocation)

    testImplementation(Dependencies.test.jUnit)
    androidTestImplementation(Dependencies.test.jUnitExt)
    androidTestImplementation(Dependencies.test.expresso)
}