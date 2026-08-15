// TeleVip settings UI adapter.
// This module is only used at build time: its debug APK's dex is extracted and
// embedded into the :app module (see `copyLargestDexAsJava` in app/build.gradle.kts),
// then injected at runtime into Telegram's classloader by DexInjector.
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.televip.SettingsAdapter"

    defaultConfig {
        applicationId = "com.televip.SettingsAdapter"
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // compileOnly: these classes are resolved from the Telegram app at runtime,
    // they are not bundled into the injected dex.
    compileOnly(libs.annotation)
    compileOnly(libs.recyclerview)
}
