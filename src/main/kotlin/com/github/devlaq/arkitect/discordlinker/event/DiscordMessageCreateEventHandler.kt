package com.github.devlaq.arkitect.discordlinker.event

import com.github.devlaq.arkitect.discordlinker.DiscordLinker
import com.github.devlaq.arkitect.util.console.Logging
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.flow.toList
import mindustry.gen.Groups

suspend fun discordMessageCreateEventHandler(e: MessageCreateEvent) {
    if(e.message.author?.isBot == true) return

    if(DiscordLinker.settings.linked.contains(e.message.channelId.toString())) {
        val highestRoleColor = e.member?.roles?.toList()?.sortedBy { it.rawPosition }?.asReversed()?.firstOrNull()?.color
        val hex = highestRoleColor?.let { "#${Integer.toHexString(highestRoleColor.rgb)}" }
        val markup = "[${hex ?: "white"}]"
        Groups.player.each {
            it.sendMessage(Logging.formatClient(DiscordLinker.bundleManager, "[sky][[[]$markup${e.member?.displayName}[][sky]]:[][] ${e.message.content}"))
        }
    }
}
