plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "../core-dependencies.gradle")
apply(from = "../common.gradle")

android {
    namespace = "deu.ac.kr.csw.domain"
}


