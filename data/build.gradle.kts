plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

apply(from = "../core-dependencies.gradle")
apply(from = "../common.gradle")

android {
    namespace = "deu.ac.kr.csw.data"
}

dependencies {

    // 코틀린 액티비티 및 뷰모델
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-rc01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0-rc01")

    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation(project(path = ":domain"))
}

