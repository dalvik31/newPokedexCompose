plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.services)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.dalvik.newpokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dalvik.newpokedex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // If your android version `targetSdk` and `compileSdk` <= 33, you need to add this line
    project.configurations.configureEach {
        resolutionStrategy {
            force("androidx.emoji2:emoji2-views-helper:1.3.0")
            force("androidx.emoji2:emoji2:1.3.0")
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /**Dagger hilt*/
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    /**FIREBASE*/
    /**Bom*/
    implementation(platform(libs.firebase.bom))
    /**Auth*/
    implementation(libs.firebase.auth.ktx)

    /**Jetpack Compose Material icons*/
    implementation(libs.androidx.material.icons.extended)

    /**Jetpack Compose ViewModel support*/
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    /**Jetpack Compose Navigation Support*/
    implementation(libs.androidx.navigation.compose)

    /**Download Images*/
    implementation (libs.coil.compose)

    /**Google fonts*/
    implementation(libs.androidx.ui.text.google.fonts)



}