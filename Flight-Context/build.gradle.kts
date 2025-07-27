dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.axonframework:axon-spring-boot-starter:4.11.2")
    implementation("org.springframework.retry:spring-retry:2.0.12")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.3.0")
    runtimeOnly("org.postgresql:postgresql")
}


tasks.register("prepareKotlinBuildScriptModel"){}