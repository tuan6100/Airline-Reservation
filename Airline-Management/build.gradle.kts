extra["springCloudVersion"] = "2025.0.0"

dependencies {
    implementation(project(":Common"))
    implementation("org.springframework.cloud:spring-cloud-stream:4.3.0")
    implementation("org.apache.kafka:kafka-streams:4.0.0")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-streams:4.3.0")
    implementation("org.springframework.retry:spring-retry:2.0.12")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.3.0")
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder:4.3.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.register("prepareKotlinBuildScriptModel"){}