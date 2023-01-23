import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

group = "com.github.devlaq.arkitect.discordlinker"
version = "1.0-SNAPSHOT"

val testServerDirectory: String? by project
val mindustryVersion: String by project

val localArkitectPath: String? by project

val moduleVersion: String by project
/**
 * Generate plugin.json
 */
fun generateModuleMeta() {
    val template = File("./module.template.json")
    val dest = File("./src/main/resources/module.json")

    val placeholders = mapOf<String, Any?>(
        "version" to moduleVersion
    )

    var replaced = template.readText()
    placeholders.forEach { (t, u) ->
        replaced = replaced.replace("\${${t}}", u.toString())
    }

    if(!dest.exists()) dest.createNewFile()
    dest.writeText(replaced)
}

generateModuleMeta()

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}
dependencies {

    testImplementation(kotlin("test"))

    if(localArkitectPath != null) compileOnly(files(localArkitectPath!!))
    else compileOnly("com.github.TeamArkitect:Arkitect:development-e1918d4f28-1")

    compileOnly("com.github.Anuken.Arc:arc-core:$mindustryVersion")
    compileOnly("com.github.Anuken.Arc:flabel:$mindustryVersion")
    compileOnly("com.github.Anuken.Mindustry:core:$mindustryVersion")
    compileOnly("com.github.Anuken.Mindustry:server:$mindustryVersion")

    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect:1.7.20")

    implementation("dev.kord:kord-core:0.8.0-M17")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

val fatJar = task("fatJar", type = Jar::class) {
    archiveBaseName.set("${project.name}-FAT")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

task("buildAndCopyJar") {
    dependsOn(fatJar)

    val jarFile = fatJar.archiveFile.get().asFile
    val destFile = File("${testServerDirectory}/config/mods/Arkitect/modules/DiscordLinker-TEST.jar")

    if(testServerDirectory != null) {
        doLast {
            jarFile.copyTo(destFile, overwrite = true)
        }
    }
}
