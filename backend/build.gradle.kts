plugins {
    java
    id("io.quarkus")
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

// ----------------------
// Build frontend + copy
// ----------------------
val buildFrontend = tasks.register<Exec>("buildFrontend") {
    workingDir = file("../frontend") // ton dossier angular
    if (System.getProperty("os.name").lowercase().contains("windows")) {
        commandLine("cmd", "/c", "npm", "install")
    } else {
        commandLine("npm", "install")
    }   // si nécessaire
}

val ngBuild = tasks.register<Exec>("ngBuild") {
    dependsOn(buildFrontend)
    workingDir = file("../frontend")
    commandLine("cmd", "/c", "where npm")
    if (System.getProperty("os.name").lowercase().contains("windows")) {
        commandLine("cmd", "/c", "npm", "run", "build")
    } else {
        commandLine("npm", "run", "build")
    }
}

// Copier le dist dans resources
val copyFrontend = tasks.register<Copy>("copyFrontend") {
    dependsOn(ngBuild)
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