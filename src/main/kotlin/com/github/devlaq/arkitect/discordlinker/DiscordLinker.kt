package com.github.devlaq.arkitect.discordlinker

import com.github.devlaq.arkitect.Arkitect
import com.github.devlaq.arkitect.core.module.ArkitectModule
import com.github.devlaq.arkitect.discordlinker.discord.ClientManager
import com.github.devlaq.arkitect.discordlinker.event.Events
import com.github.devlaq.arkitect.discordlinker.utils.DataFile
import com.github.devlaq.arkitect.i18n.BundleManager
import com.github.devlaq.arkitect.util.console.Logger
import java.util.*

class DiscordLinker: ArkitectModule {

    class EventType {
        class ModuleEnableEvent
        class ModuleDisableEvent
        class KordInitializeEvent
    }

    private val logger = createLogger("Arkitect/DiscordLinker")

    companion object {
        lateinit var settings: DiscordLinkerSettings

        val bundleManager = BundleManager()

        fun createLogger(tag: String) = Logger(tag, bundleManager)
    }

    override fun enable() {
        settings = DiscordLinkerSettings.load(DataFile("settings.json", mkdirsParent = true))


        bundleManager.localeProvider = {
            Locale.forLanguageTag(Arkitect.settings?.locale ?: Locale.getDefault().toLanguageTag())
        }
        bundleManager.loadBundles(
            Locale.KOREAN,
            Locale.ENGLISH,
            path = "/discord_linker/translations/bundle_{languageTag}.properties",
            clazz = javaClass
        )

        logger.infoln("<%module.enabling%>")

        Events.registerServerEvents()
        ClientManager.init()

        arc.Events.fire(EventType.ModuleEnableEvent())
    }

    override fun disable() {
        logger.infoln("<%module.disabling%>")

        arc.Events.fire(EventType.ModuleDisableEvent())

        ClientManager.dispose()
        Events.removeServerEvents()

        bundleManager.clear()
    }

}
