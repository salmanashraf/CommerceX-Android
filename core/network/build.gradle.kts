plugins {
	alias(libs.plugins.android.library)
}

android {
	namespace = "com.sa.core.network"
	compileSdk {
		version = release(36)
	}

	defaultConfig {
		minSdk = 28
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
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp.logging)
	implementation(libs.koin.android)
}
