package com.reaszki.pocketin.config_module

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.area_module.WandConsumeCommand
import com.reaszki.pocketin.area_module.commands.AreaLookUpCommand
import com.reaszki.pocketin.event_module.OnBlockBreakEvent
import com.reaszki.pocketin.event_module.OnPlayerInteractEvent
import org.bukkit.block.data.type.Wall
import org.bukkit.plugin.java.JavaPlugin

object Registry {
    fun consume() {
        fileManager()
        fillerMananger()
        eventManager()
        commandManager()
    }

    fun fileManager() {
        Files.createConfigFolder(Plugin.plugin)
        Files.createSubFolder(Plugin.plugin, "areas")
    }

    fun eventManager() {
        Plugin.plugin.server.pluginManager.registerEvents(OnBlockBreakEvent(), Plugin.plugin)
        Plugin.plugin.server.pluginManager.registerEvents(OnPlayerInteractEvent(), Plugin.plugin)
    }

    fun commandManager() {
        Plugin.plugin.getCommand("wandconsume")?.setExecutor(WandConsumeCommand())
        Plugin.plugin.getCommand("arealookup")?.setExecutor(AreaLookUpCommand())
    }

    fun fillerMananger() {
        PocketArea.loadAreas()
    }
}