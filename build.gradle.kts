plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url="https://repo.spring.io/milestone")  // Spring milestones
    maven(url="https://repo.spring.io/snapshot")   // Spring snapshots
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.5.6"))
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))

    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
}