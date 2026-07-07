plugins {
    alias(libs.plugins.android.application)

    // Firebase
    id("com.google.gms.google-services")
}

android {

    namespace = "com.example.kupa"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.kupa"

        minSdk = 24
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_1_8

        targetCompatibility =
            JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    // ✅ FIX META-INF ERRORS
    packaging {

        resources {

            excludes += "META-INF/LICENSE.md"

            excludes += "META-INF/LICENSE-notice.md"

            excludes += "META-INF/NOTICE.md"

            excludes += "META-INF/NOTICE"

            excludes += "META-INF/LICENSE"

            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {

    // Android basics
    implementation(libs.appcompat)

    implementation(libs.material)

    implementation(libs.activity)

    implementation(libs.constraintlayout)

    implementation(libs.navigation.fragment)

    implementation(libs.navigation.ui)

    // Firebase
    implementation(
        platform(
            "com.google.firebase:firebase-bom:34.11.0"
        )
    )

    implementation(
        "com.google.firebase:firebase-analytics"
    )

    implementation(
        "com.google.firebase:firebase-auth"
    )

    implementation(
        "com.google.firebase:firebase-firestore"
    )

    implementation(libs.firebase.database)

    // Google Maps & Location
    implementation(libs.play.services.maps)

    implementation(libs.play.services.location)

    // Glide
    implementation(libs.glide)

    annotationProcessor(libs.compiler)

    // UI Libraries
    implementation(libs.pinview)

    implementation(libs.viewpager2)

    implementation(libs.circleindicator)

    // Email OTP Libraries
    implementation(
        "com.sun.mail:android-mail:1.6.7"
    )

    implementation(
        "com.sun.mail:android-activation:1.6.7"
    )

    // Testing
    testImplementation(libs.junit)

    androidTestImplementation(libs.ext.junit)

    androidTestImplementation(libs.espresso.core)
}