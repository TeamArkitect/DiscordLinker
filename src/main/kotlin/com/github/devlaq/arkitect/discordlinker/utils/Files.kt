package com.github.devlaq.arkitect.discordlinker.utils

import java.io.File

fun dataDirectory() = File("config/mods/Arkitect/modules/DiscordLinker")

class DataFile(pathname: String, mkdirs: Boolean = false, createNewFile: Boolean = false, mkdirsParent: Boolean = false): File(dataDirectory(), pathname) {
    init {
        if(createNewFile && !this.exists()) {
            parentFile.mkdirs()
            this.createNewFile()
        }
        if(mkdirsParent) {
            parentFile.mkdirs()
        }
        else if(mkdirs && !this.exists()) this.mkdirs()
    }
}
