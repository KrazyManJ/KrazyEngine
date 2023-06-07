plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("org.jetbrains:annotations:24.0.1")
    compileOnly("org.spigotmc:spigot:1.19.4-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
}

group = "me.KrazyManJ"
version = "1.0.0"
description = "KrazyEngine"

fun excludingLib(compiled: Boolean): List<String> {
    val classifier = if (compiled) "class" else "java"
    return listOf(
        "**/*.yml",
        "me/KrazyManJ/KrazyEngine/BungeeMain.${classifier}",
        "me/KrazyManJ/KrazyEngine/SpigotMain.${classifier}"
    )
}

val pluginJar by tasks.registering(Jar::class) {
    from(sourceSets.main.get().output)
    archiveClassifier.set("plugin")
}

java {
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.jar {
    dependsOn(pluginJar)
    exclude(excludingLib(true))
}

tasks.javadoc {
    destinationDir = file("${rootProject.rootDir}/javadoc")
    exclude(excludingLib(false))
}