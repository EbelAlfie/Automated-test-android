plugins {
    id("java")
}

group = "org.example.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation group: 'io.cucumber', name: 'cucumber-spring', version: '7.1.0'

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36'")

    implementation("io.appium:java-client:9.3.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testng:testng:7.9.0")
}

tasks.test {
    useJUnitPlatform()
    useTestNG()
    testLogging {
        showStandardStreams = true // Enables System.out and System.err logs
    }
}