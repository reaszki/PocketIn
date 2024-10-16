package com.reaszki.pocketin.area_module.commands

import com.reaszki.pocketin.area_module.PocketArea
import com.reaszki.pocketin.area_module.PocketWand
import com.reaszki.pocketin.config_module.NBT
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player

object PocketCommandTree {

    var pocket: CommandAPICommand = CommandAPICommand("pocket")
        .withSubcommand(CommandAPICommand("area")
            .withSubcommand(CommandAPICommand("wand")
                .executes(CommandExecutor {
                    commandSender, commandArguments ->
                    if (commandSender is Player) wand(commandSender)
                }))
            .withSubcommand(CommandAPICommand("create")
                .withArguments(GreedyStringArgument("name"))
                .executes(CommandExecutor { commandSender, commandArguments ->
                    if (commandSender is Player) create(commandSender, commandArguments)
                }))
            .withSubcommand(CommandAPICommand("load")   
                .executes(CommandExecutor { commandSender, commandArguments ->
                    if (commandSender is Player) load()
                }))
            .withSubcommand(CommandAPICommand("lookup")
                .executes(CommandExecutor { commandSender, commandArguments ->
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
    
    private fun wand(sender: Player) {
        sender.inventory.addItem(PocketWand.item())
        sender.playSound(sender.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
        sender.sendMessage("")
        sender.sendMessage("§6§lPocketIn")
        sender.sendMessage("Received a selection wand.")
        sender.sendMessage("")
    }

    fun create(sender: Player, args: CommandArguments) {
        if (sender.inventory.itemInMainHand.type == Material.AIR && !NBT.lookForCompound(sender.inventory.itemInMainHand, "pocketin")) return
        var hasArgs = args.get("name").toString().isEmpty().not()
        if (!hasArgs) return
        if (sender.inventory.itemInMainHand.amount == 0) return
        PocketArea.createArea(
            args.get("name") as String, sender.world.name,
            NBT.retriveWandPos(sender.inventory.itemInMainHand,"pos1"),
            NBT.retriveWandPos(sender.inventory.itemInMainHand,"pos2")
        )
        sender.inventory.itemInMainHand.amount = 0
        sender.sendMessage("§aArea created (${args[0]})!")

        return
    }

    private fun load() {
        PocketArea.loadAreas()
    }

    private fun lookup(sender: Player) {
        var areas = PocketArea.lookUpArea(sender.location)
                    if (areas.isEmpty()) { sender.sendMessage("No areas at current location."); return }
                    areas.forEach {area -> sender.sendMessage("Area: ${area!!.name}") }
    }
}