package com.github.devlaq.arkitect.discordlinker

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private val json = Json {
    encodeDefaults = true
    prettyPrint = true
    ignoreUnknownKeys = true
}

@Suppress("ArrayInDataClass")
@Serializable
data class DiscordLinkerSettings(
    val token: String = "BOT_TOKEN",
    val linked: Array<String> = arrayOf()
) {

    companion object {
        fun load(file: File): DiscordLinkerSettings {
            return if(!file.exists()) {
                file.createNewFile()
                val settings = DiscordLinkerSettings()
                settings.save(file)
                settings
            }
            else json.decodeFromString(file.readText())
        }
    }

    fun save(file: File) {
        if(!file.exists()) file.createNewFile()
        file.writeText(json.encodeToString(this))
    }
}
