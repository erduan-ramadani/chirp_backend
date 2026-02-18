plugins {
    id("chirp.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.ercoding"
version = "unspecified"

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}