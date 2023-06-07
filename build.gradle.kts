import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
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

java {
    withJavadocJar()
}

fun registerShadowJarTask(classifier: String ,excludes: List<String>): RegisteringDomainObjectDelegateProviderWithTypeAndAction<out TaskContainer, ShadowJar> {
    return tasks.registering(ShadowJar::class){
        from(project.extensions.findByType(JavaPluginExtension::class)?.sourceSets?.main?.get()?.output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
        archiveClassifier.set(classifier)
        minimize()
        exclude("META-INF/INDEX.LIST", "META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
        exclude(excludes)
    }
}

val libJar by registerShadowJarTask("",excludingLib(true))
val pluginJar by registerShadowJarTask("plugin", listOf())

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<Javadoc> {
        options.encoding = "UTF-8"
    }
    jar {
        enabled = false
    }
    javadoc {
        destinationDir = file("${rootProject.rootDir}/javadoc")
        exclude(excludingLib(false))
    }
    shadowJar {
        dependsOn(libJar, pluginJar)
        group = "custom"
        enabled = false
    }
    publishToMavenLocal {
        group = "custom"
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(libJar.get())
    }
}