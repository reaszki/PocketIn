package com.reaszki.pocketin

import com.reaszki.pocketin.config_module.Plugin
import com.reaszki.pocketin.config_module.Registry
import org.bukkit.plugin.java.JavaPlugin

class PocketIn : JavaPlugin() {

    override fun onEnable() {
        Plugin.define(this)
        Registry.consume()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
