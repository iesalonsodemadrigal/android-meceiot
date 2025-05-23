import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))


android {
    namespace = "edu.iesam.meceiot"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.iesam.meceiot"
        minSdk = 24
        targetSdk = 35
        versionCode = 4
        versionName = "0.2.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    //Gson
    implementation(libs.gson.serializer)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.log.interceptor)
    //Koin
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    implementation(libs.androidx.fragment.ktx)
    ksp(libs.koin.ksp)
    //MockK
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    //JUnit
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.engine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Vico
    implementation(libs.core)
    implementation(libs.views)
    //Room
    implementation(libs.room.runtime)
    ksp(libs.room.ksp)
    implementation(libs.room.coroutines)
    //coil
    implementation(libs.coil)
    //Skeleton
    implementation (libs.skeletonlayout)
    //Kotlin Coroutines Test
    testImplementation(libs.kotlinx.coroutines.test)
    //Firebase
    implementation(project.dependencies.platform(libs.firebaseBom))
    //Firestore
    implementation(libs.firebase.firestore)
    //SwipeToRefresh
    implementation(libs.androidx.swiperefreshlayout)

}
