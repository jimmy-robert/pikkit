plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "social.pikkit.core.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(projects.core.common)

    implementation(platform(libs.androidx.compose.bom))

    api(libs.androidx.constraintlayout.compose)
    api(libs.androidx.material3)
    api(libs.androidx.navigation)

    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)

    api(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}