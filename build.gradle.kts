import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "currencyRatesNBU"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.6.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework:spring-web:5.3.12")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.13.0")

    // https://mvnrepository.com/artifact/org.liquibase/liquibase-core
    implementation ("org.liquibase:liquibase-core:4.6.0")

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation ("org.postgresql:postgresql:42.3.1")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa:2.6.0")
    // https://mvnrepository.com/artifact/org.modelmapper/modelmapper
    implementation ("org.modelmapper:modelmapper:2.4.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
