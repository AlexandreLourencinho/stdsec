import com.github.gradle.node.npm.task.NpmTask


plugins {
    java
    id("io.quarkus")
    id("com.github.node-gradle.node") version "7.0.2"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-rest")
    implementation("io.quarkus:quarkus-flyway")
    implementation("io.quarkus:quarkus-oidc")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-hibernate-orm")
    testImplementation("io.quarkus:quarkus-junit")
    testImplementation("io.rest-assured:rest-assured")
}

group = "org.stdsec"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

node {
    version.set("20.17.0")
    download.set(true)
    nodeProjectDir.set(file("../frontend"))
}

// ----------------------
// Build frontend + copy
// ----------------------
val npmBuild = tasks.register<NpmTask>("npmBuild") {
    dependsOn(tasks.named("npmInstall"))
    args.set(listOf("run", "build"))
}

// Copier le dist dans resources
val copyFrontend = tasks.register<Copy>("copyFrontend") {
    dependsOn(npmBuild)
    from("../frontend/dist/stdsec-frontend/browser")
    into(layout.buildDirectory.dir("resources/main/META-INF/resources"))
}

// S’assurer que le backend build attend le frontend
tasks.named("build") {
    dependsOn(copyFrontend)
}

tasks.named<ProcessResources>("processResources") {
    dependsOn(copyFrontend)
}