package com.github.devlaq.arkitect.discordlinker.event

import com.github.devlaq.arkitect.EventManager
import com.github.devlaq.arkitect.discordlinker.DiscordLinker
import com.github.devlaq.arkitect.discordlinker.discord.ClientManager
import com.github.devlaq.arkitect.util.console.Logger
import dev.kord.core.on

object Events {

    private val logger = Logger("Events")

    fun registerServerEvents() {
        EventManager.register(DiscordLinker::class.java, ::kordInitializeEventHandler)
        EventManager.register(DiscordLinker::class.java, ::moduleDisableEventHandler)
        EventManager.register(DiscordLinker::class.java, ::playerChatEventHandler)
    }

    fun removeServerEvents() {
        EventManager.removeForClass(DiscordLinker::class.java)
    }

    fun registerDiscordEvents() {
        if(!ClientManager.kordInitialized) {
            logger.errorln("Can't register discord event handlers: kord is not initialized yet!")
        }
        arrayOf(
            ::discordMessageCreateEventHandler
        ).forEach {
            ClientManager.kord.on(consumer = it)
        }
    }

    // Removing discord events is unnecessary. kord's events are reset when module load
}
