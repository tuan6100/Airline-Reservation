group = "vn.edu.hust"
version = "0.0.1-SNAPSHOT"

extra["springCloudVersion"] = "2025.0.0"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-stream:4.3.0")
	implementation("org.axonframework:axon-spring-boot-starter:4.11.2")
	implementation("org.apache.kafka:kafka-streams:4.0.0")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-streams:4.3.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder:4.3.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.register("prepareKotlinBuildScriptModel"){}

