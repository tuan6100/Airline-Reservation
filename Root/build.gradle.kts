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

	dependencies {
		add("implementation", "org.springframework.boot:spring-boot-starter-web")
		add("implementation", "org.springframework.boot:spring-boot-starter-actuator")
		add("implementation", "org.springframework.boot:spring-boot-starter-data-jpa")
		add("implementation", "org.springframework:spring-aop")
		add("implementation", "me.paulschwarz:spring-dotenv")
		add("implementation", "com.fasterxml.uuid:java-uuid-generator")
		add("compileOnly", "org.projectlombok:lombok")
		add("annotationProcessor", "org.projectlombok:lombok")
		add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
		add("runtimeOnly", "org.postgresql:postgresql")
		add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}