package com.github.devlaq.arkitect.discordlinker.event

import com.github.devlaq.arkitect.discordlinker.DiscordLinker
import com.github.devlaq.arkitect.discordlinker.discord.ClientManager
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.asChannelOf
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.channel.TextChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mindustry.game.EventType

fun playerChatEventHandler(e: EventType.PlayerChatEvent) {
    CoroutineScope(Dispatchers.Default).launch {
        val kord = ClientManager.kord
        DiscordLinker.settings.linked.forEach {
            val channel = kord.getChannel(Snowflake(it))?.asChannelOf<TextChannel>()
            channel?.createMessage {
                content = "${if(e.player.admin) "__**${e.player.name()}**__" else "__${e.player.name()}__"} >> ${e.message}"
            }
        }
    }
}
