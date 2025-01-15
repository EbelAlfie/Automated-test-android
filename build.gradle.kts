plugins {
    id("java")
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")

    implementation("io.cucumber:cucumber-java:7.1.0")
    implementation("io.cucumber:cucumber-testng:7.1.0")
    testImplementation("io.cucumber:cucumber-spring:7.1.0")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.appium:java-client:9.3.0")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useTestNG()
    testLogging {
        showStandardStreams = true // Enables System.out and System.err logs
    }
}