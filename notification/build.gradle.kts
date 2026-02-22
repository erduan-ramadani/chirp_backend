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

    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.thymeleaf)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}