import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.jpa)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.kotlin.spring)
}

group = "com.ercoding"
version = "0.0.1"

tasks {
	named<BootJar>("bootJar") {
		from(project(":notification").projectDir.resolve("src/main/resources")) {
			into("")
		}
		from(project(":user").projectDir.resolve("src/main/resources")) {
			into("")
		}
	}
}
//
repositories {
	mavenCentral()
}

dependencies {

	implementation(libs.kotlin.stdlib)
	implementation(libs.kotlin.reflect)
	implementation(libs.spring.boot.starter.security)

	implementation(libs.spring.boot.starter.mail)
	implementation(libs.spring.boot.starter.amqp)
	implementation(libs.spring.boot.starter.data.redis)
	implementation(libs.spring.boot.starter.data.jpa)
	runtimeOnly(libs.postgresql)
}