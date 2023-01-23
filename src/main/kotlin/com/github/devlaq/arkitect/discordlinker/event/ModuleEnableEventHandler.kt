package com.github.devlaq.arkitect.discordlinker.event

import com.github.devlaq.arkitect.discordlinker.DiscordLinker
import com.github.devlaq.arkitect.discordlinker.discord.ClientManager
import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.asChannelOf
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.channel.TextChannel
import dev.kord.rest.builder.message.create.embed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun kordInitializeEventHandler(e: DiscordLinker.EventType.KordInitializeEvent) {
    CoroutineScope(Dispatchers.Default).launch {
        val kord = ClientManager.kord
        DiscordLinker.settings.linked.forEach {
            val channel = kord.getChannel(Snowflake(it))?.asChannelOf<TextChannel>()
            channel?.createMessage {
                embed {
                    title = "Server online"
                    description = "Connected to the server."
                    color = Color(0, 255, 0)
                }
            }
        }
    }
}
