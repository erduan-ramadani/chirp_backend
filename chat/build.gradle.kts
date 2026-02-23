plugins {
    id("chirp.spring-boot-service")
    kotlin("plugin.jpa")
    kotlin("plugin.spring") version "2.1.21"
}

group = "com.ercoding"
version = "unspecified"

dependencies {
    implementation(projects.common)

    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.amqp)
    runtimeOnly(libs.postgresql)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}