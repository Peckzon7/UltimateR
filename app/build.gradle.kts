plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.ultimaterage.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ultimaterage.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    // O alvo inicial é ARM64. O núcleo de emulação deverá fornecer bibliotecas
    // nativas compatíveis com esta ABI quando for integrado.
    ndk {
        abiFilters += listOf("arm64-v8a")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
