import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.mavenCentral
import org.gradle.kotlin.dsl.repositories

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone" )}
    }
}

rootProject.name = "chirp"

include("app")
include("user")
include("chat")
include("notification")
include("common")