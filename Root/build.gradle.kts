plugins {
	id("org.springframework.boot") version "3.5.3" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	group = "vn.edu.hust"
	version = "0.0.1-SNAPSHOT"

	configure<JavaPluginExtension> {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(24))
		}
	}

	configurations {
		getByName("compileOnly") {
			extendsFrom(configurations.getByName("annotationProcessor"))
		}
	}

	val mockitoAgent = configurations.create("mockitoAgent")

	dependencies {
		add("implementation", "org.springframework.boot:spring-boot-starter-web")
		add("implementation", "org.springframework.boot:spring-boot-starter-actuator")
		add("implementation", "org.springframework.boot:spring-boot-starter-data-jpa")
		add("implementation", "org.springframework:spring-aop")
		add("implementation", "me.paulschwarz:spring-dotenv:4.0.0")
		add("implementation", "com.fasterxml.uuid:java-uuid-generator:5.1.0")
		add("implementation", "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
		add("compileOnly", "org.projectlombok:lombok")
		add("annotationProcessor", "org.projectlombok:lombok")
		add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
		add("runtimeOnly", "org.postgresql:postgresql")
		add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
		add("mockitoAgent", "org.mockito:mockito-core") { isTransitive = false }
    }

	tasks.withType<Test> {
		useJUnitPlatform()
		jvmArgs = listOf("-Xshare:off", "-javaagent:${mockitoAgent.asPath}")
	}


}