package com.reaszki.pocketin.area_module.commands

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.area_module.PocketWand
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.Sound
import org.bukkit.entity.Player

object PocketCommand {
    var pocket = CommandAPICommand("pocket")
        .withSubcommand(CommandAPICommand("area")
            .withSubcommand(CommandAPICommand("wand")
                .executes(CommandExecutor {
                    commandSender, commandArguments ->
                    if (commandSender is Player) wand(commandSender)
                }))
            .withSubcommand(CommandAPICommand("load")
                .executes(CommandExecutor {
                        commandSender, commandArguments ->
                    if (commandSender is Player) load()
                }))
            .withSubcommand(CommandAPICommand("lookup")
                .executes(CommandExecutor {
                        commandSender, commandArguments ->
                    if (commandSender is Player) lookup(commandSender)
                }))
            .executes(CommandExecutor { commandSender, commandArguments ->
                if (commandSender is Player) {
                    commandSender.sendMessage("")
                    commandSender.sendMessage("§6§lPocketIn §f§lArea")
                    commandSender.sendMessage("/pocket area wand §7Gives you a selection wand.")
                    commandSender.sendMessage("/pocket area create <name> §7Wile still holding the wand use the command with the area name.")
                    commandSender.sendMessage("/pocket area load §7Reload areas from /areas/ folder.")
                    commandSender.sendMessage("/pocket area lookup §7List areas at current location.")
                    commandSender.sendMessage("")
                }
            })
        )
        .executes(CommandExecutor { commandSender, commandArguments ->
            if (commandSender is Player) {
                commandSender.sendMessage("")
                commandSender.sendMessage("§6§lPocketIn")
                commandSender.sendMessage("/pocket area §7PocketArea related commands.")
                commandSender.sendMessage("")
            }

        })

    fun wand(sender: Player) {
        sender.inventory.addItem(PocketWand())
        sender.playSound(sender.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
        sender.sendMessage("")
        sender.sendMessage("§6§lPocketIn")
        sender.sendMessage("Received a selection wand.")
        sender.sendMessage("")
    }

    fun create() {}

    fun load() {
        PocketArea.loadAreas()
    }

    fun lookup(sender: Player) {
        var areas = PocketArea.lookUpArea(sender.location)
                    if (areas.isEmpty()) { sender.sendMessage("No areas at current location."); return }
                    areas.forEach {area -> sender.sendMessage("Area: ${area!!.name}") }
    }
}