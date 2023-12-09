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

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.4.0-alpha01")


    // Lottie (Vector Animation)
    implementation("com.airbnb.android:lottie:6.2.0")

    // google G Button
    implementation ("com.github.TutorialsAndroid:GButton:v1.0.19")


    implementation(project( path = ":data"))
    implementation(project( path = ":domain"))

    // Chat UI Library
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    modules {
        module("com.google.android:flexbox"){
            replacedBy("com.google.android.flexbox:flexbox")
        }
    }

    implementation("com.github.stfalcon-studio:Chatkit:v0.4.1"){
        exclude("com.google.android", "flexbox")
    }


}

kapt {
    correctErrorTypes = true
}

