plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.sqilt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sqilt"
        minSdk = 24
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
}

dependencies {

    implementation ("androidx.appcompat:appcompat:$rootProject.appCompatVersion")

// Dependencies for working with Architecture components
// You'll probably have to update the version numbers in build.gradle (Project)


// Room components
    implementation ("androidx.room:room-runtime:$rootProject.roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$rootProject.roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$rootProject.roomVersion")

// Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$rootProject.lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata:$rootProject.lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

