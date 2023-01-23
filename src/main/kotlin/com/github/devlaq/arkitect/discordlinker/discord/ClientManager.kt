package com.github.devlaq.arkitect.discordlinker.discord

import com.github.devlaq.arkitect.discordlinker.DiscordLinker
import com.github.devlaq.arkitect.discordlinker.event.Events
import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.*
import kotlin.concurrent.thread

object ClientManager {

    private val logger = DiscordLinker.createLogger("DiscordLinker/ClientManager")

    lateinit var kord: Kord
    val kordInitialized get() = ::kord.isInitialized
    var initialized = false


    fun init() {
        if(initialized) return
        thread {
            CoroutineScope(Dispatchers.IO).launch {
                logger.infoln("<%client.initializing%>")
                try {
                    kord = Kord(DiscordLinker.settings.token)
                } catch (t: Throwable)  {
                    if(t.message?.contains("Unauthorized") == true) {
                        logger.infoln("<%client.initialize_failed%>", "<%client.token_invalid%>")
                    } else logger.infoln("<%client.initialize_failed%>", t.localizedMessage)
                    return@launch
                }

                initialized = true

                Events.registerDiscordEvents()

                arc.Events.fire(DiscordLinker.EventType.KordInitializeEvent())

                kord.login {
                    @OptIn(PrivilegedIntent::class)
                    intents += Intent.MessageContent
                }
            }
        }
    }

    fun dispose() {
        CoroutineScope(Dispatchers.Default).launch {
            kord.shutdown()
        }
    }

}
