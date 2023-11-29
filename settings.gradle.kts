rootProject.name = "BeepBeep"

include(":api_gateway")
include(":service_chat")
include(":service_identity")
include(":service_location")
include(":service_notification")
include(":service_restaurant")
include(":service_taxi")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String

        kotlin("jvm").version(kotlinVersion)
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
