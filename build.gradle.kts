import org.springdoc.openapi.gradle.plugin.OpenApiGeneratorTask

plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	id("org.springdoc.openapi-gradle-plugin") version "1.7.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

tasks.withType<OpenApiGeneratorTask> {
	// this will break the configuration on 1.7.0
	// because the task tries to load the apiDocsUrl before it is set
	// by the openApi extension block
	println("I am in the OpenApiGeneratorTask block")
}

// move this block above the tasks.withType<OpenApiGeneratorTask>
// to fix the issue
openApi {
	println("I am in the OpenApiExtension block")
	apiDocsUrl.set("http://localhost:8085/v3/api-docs")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.2.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
}