package com.reaszki.pocketin.config_module

import org.bukkit.plugin.PluginLogger
import org.bukkit.plugin.java.JavaPlugin

object Plugin {
    lateinit var plugin: JavaPlugin
    private var shout = true

    fun define(plugin: JavaPlugin) {
        this.plugin = plugin
    }

    enum class LogType {
        INFO,
        SEVERE,
        WARNING
    }

    fun log(message: String, type: LogType = LogType.INFO, easyRead: Boolean = false) {
        if (shout) {
            var logger = PluginLogger.getLogger("PocketIn")
            if (easyRead) {println()}
            when (type) {
                LogType.INFO -> logger.info(message)
                LogType.SEVERE -> logger.severe(message)
                LogType.WARNING -> logger.warning(message)
            }
            if (easyRead) {println()}
        }
    }
}