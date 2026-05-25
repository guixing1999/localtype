// SPDX-FileCopyrightText: 2015 - 2024 Rime community
//
// SPDX-License-Identifier: GPL-3.0-or-later

plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version embeddedKotlinVersion
}

group = "com.localtype.app.build_logic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(libs.kotlinx.serialization.json)
}

gradlePlugin {
    plugins {
        register("androidAppConvention") {
            id = "com.localtype.app.app-convention"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("dataChecksums") {
            id = "com.localtype.app.data-checksums"
            implementationClass = "DataChecksumsPlugin"
        }
        register("nativeAppConvention") {
            id = "com.localtype.app.native-app-convention"
            implementationClass = "NativeAppConventionPlugin"
        }
        register("nativeCacheHash") {
            id = "com.localtype.app.native-cache-hash"
            implementationClass = "NativeCacheHashPlugin"
        }
        register("openccData") {
            id = "com.localtype.app.opencc-data"
            implementationClass = "OpenCCDataPlugin"
        }
    }
}
