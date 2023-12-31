plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

apply(from = "../core-dependencies.gradle")
apply(from = "../common.gradle")

android {
    namespace = "deu.ac.kr.csw.chatting"

    defaultConfig {
        applicationId = "deu.ac.kr.csw.chatting"
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

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // 코틀린 액티비티 및 뷰모델
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-rc01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0-rc01")

    // 코틀린 Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Lottie (Vector Animation)
    implementation("com.airbnb.android:lottie:6.2.0")

    // google G Button
    implementation("com.github.TutorialsAndroid:GButton:v1.0.19")

    // Picasso (Image Loading)
    implementation("com.squareup.picasso:picasso:2.8")

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")




    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation(project(path = ":data"))
    implementation(project(path = ":domain"))


}

kapt {
    correctErrorTypes = true
}

