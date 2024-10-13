package com.reaszki.pocketin.config_module

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.area_module.commands.PocketCommandTree
import com.reaszki.pocketin.event_module.OnBlockBreakEvent
import com.reaszki.pocketin.event_module.OnPlayerInteractEvent

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
        PocketCommandTree.pocket.register()
    }

    fun fillerMananger() {
        PocketArea.loadAreas()
    }
}