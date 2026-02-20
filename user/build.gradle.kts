plugins {
    id("java-library")
    id("chirp.spring-boot-service")
    kotlin("plugin.jpa")
    kotlin("plugin.spring") version "2.1.21"
}

group = "com.ercoding"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation(projects.common)

    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.validation)

    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)

    implementation(libs.jwt.api)
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}