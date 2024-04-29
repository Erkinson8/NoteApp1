plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.noteapp1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.noteapp1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        implementation(libs.androidx.navigation.fragment.ktx)
        implementation(libs.androidx.navigation.ui.ktx)
        implementation("androidx.viewpager2:viewpager2:1.0.0")
        implementation("me.relex:circleindicator:2.1.6")
        implementation("com.airbnb.android:lottie:3.4.0")
        val roomVersion = "2.6.1"
        implementation("androidx.room:room-ktx:$roomVersion")
        ksp("androidx.room:room-compiler:$roomVersion")
        implementation("androidx.room:room-runtime:2.4.0")

        dependencies {
            implementation(libs.androidx.ui.graphics.android)

            implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
            implementation("com.google.firebase:firebase-analytics")
            implementation(libs.firebase.auth)
            implementation("com.google.firebase:firebase-auth")
            implementation("com.google.android.gms:play-services-auth:21.1.0")
            implementation("com.google.firebase:firebase-firestore")
            implementation ("com.google.firebase:firebase-firestore-ktx:23.0.3")

        }
    }
}
dependencies {
    implementation(libs.firebase.firestore)
}
