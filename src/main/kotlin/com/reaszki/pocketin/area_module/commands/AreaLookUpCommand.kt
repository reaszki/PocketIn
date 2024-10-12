package com.reaszki.pocketin.area_module.commands

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.config_module.Plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AreaLookUpCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            var areas = PocketArea.lookUpArea(sender.location)
            if (areas.isEmpty()) { sender.sendMessage("No areas at current location."); return true }
            areas.forEach {area -> sender.sendMessage("Area: ${area!!.name}") }
            return true

        } else {
            Plugin.log("Player only command.")
            return false
        }
    }
}