import java.util.Properties

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.1")
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

java {
    withJavadocJar()
    withSourcesJar()
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("testserverfolders.properties").inputStream())

val pluginJar by tasks.registering(Jar::class) {
    from(sourceSets.main.get().output)
    archiveClassifier.set("plugin")
}



tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<Javadoc> {
        options.encoding = "UTF-8"
    }
    register("compileToBungeeTest",Jar::class) {
        from(sourceSets.main.get().output)
        destinationDirectory.set(file(localProperties.getProperty("bungee")))
        group = "AKrazyEngine"
    }
    register("compileToSpigotTest",Jar::class) {
        from(sourceSets.main.get().output)
        destinationDirectory.set(file(localProperties.getProperty("spigot")))
        group = "AKrazyEngine"
    }
    jar {
        dependsOn(pluginJar)
        archiveClassifier.set("")
        exclude(excludingLib(false))
        group = "AKrazyEngine"
    }
    javadoc {
        destinationDir = file("${rootProject.rootDir}/javadoc")
        exclude(excludingLib(false))
    }
    publishToMavenLocal {
        group = "AKrazyEngine"
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}