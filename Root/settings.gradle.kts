rootProject.name = "Airline-EDA"
include(":Flight-Service", ":Shared")
pluginManagement {
    repositories {
        mavenCentral()
    }

    plugins {
        java
        id("org.springframework.boot") version "3.5.3"
        id("io.spring.dependency-management") version "1.1.7"
        id("org.openapi.generator") version "7.14.0"
    }
}