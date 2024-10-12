package com.reaszki.pocketin.config_module

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.area_module.PocketWand
import com.reaszki.pocketin.area_module.WandConsumeCommand
import com.reaszki.pocketin.area_module.commands.AreaLookUpCommand
import com.reaszki.pocketin.area_module.commands.PocketCommand
import com.reaszki.pocketin.event_module.OnBlockBreakEvent
import com.reaszki.pocketin.event_module.OnPlayerInteractEvent
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.entity.Player

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
//        Plugin.plugin.getCommand("pocket")?.setExecutor(SlashPocketCommand())
        PocketCommand.pocket.register()
    }

    fun fillerMananger() {
        PocketArea.loadAreas()
    }
}