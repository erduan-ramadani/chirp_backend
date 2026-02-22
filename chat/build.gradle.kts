plugins {
    id("chirp.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.ercoding"
version = "unspecified"

dependencies {
    implementation(projects.common)
    
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}