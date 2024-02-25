import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.intellij)
    alias(libs.plugins.lombok)
    alias(libs.plugins.fabricloom)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktfmt)
}

repositories {
    maven("https://jitpack.io")
    maven("https://api.modrinth.com/maven")
    maven("https://maven.ladysnake.org/releases")
    maven("https://maven.cafeteria.dev")
    maven("https://maven.jamieswhiteshirt.com/libs-release")
    maven("https://masa.dy.fi/maven")
    maven("https://maven.shedaniel.me")
    maven("https://maven.terraformersmc.com")
    maven("https://maven.quiltmc.org/repository/release")
}

dependencies {
    minecraft(libs.bundles.minecraft)
    mappings(libs.bundles.yarn)
    modImplementation(libs.bundles.fabric)
    implementation(libs.bundles.vineflower)
    modImplementation(libs.bundles.origins) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    implementation(libs.bundles.night)
    include(libs.bundles.night)
    modImplementation(libs.bundles.pehkui) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation(libs.bundles.kotlin)
}

val sourceCompatibility = JavaVersion.VERSION_17
val targetCompatibility = JavaVersion.VERSION_17
val archivesBaseName = "archivesBaseName".configKey

version = "${"mod_version".configKey}+${libs.versions.minecraft.get()}"

tasks {
    processResources {
        expand(mapOf(
                "version" to version,
                "mod_id" to "mod_id".configKey,
                "loader_version" to libs.versions.fabricloader.get(),
                "minecraft_version" to libs.versions.minecraft.get(),
                "java_version" to "$sourceCompatibility",
                "pehkui_version" to libs.versions.pehkui.get(),
                "origins_version" to libs.versions.origins.fabric.get()
        ))
    }
    jar {
        from("LICENSE") {
            rename { "${it}_$archivesBaseName" }
        }
    }

    compileJava {
        options.release = 17
    }
}



intellij {
    version = "2023.3.4"
    type = "IU"
}

val String.configKey: String
    get() = project.properties[this].toString()


loom {
    serverOnlyMinecraftJar()
}

ktfmt {
    kotlinLangStyle()
}

kotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_17
    compilerOptions.verbose = true
}