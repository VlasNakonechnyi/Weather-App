plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.fivesysdev.weatherapp"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
        android.buildFeatures.buildConfig = true
    }
    defaultConfig {
        applicationId = "com.fivesysdev.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("com.google.dagger:hilt-android:2.50")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:2.50")
    annotationProcessor ("androidx.hilt:hilt-compiler:1.1.0")
    implementation ("com.google.dagger:dagger:2.50")
    implementation ("com.google.dagger:dagger-android-support:2.50")

}