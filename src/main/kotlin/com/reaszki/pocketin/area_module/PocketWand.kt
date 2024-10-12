package com.reaszki.pocketin.area_module

import com.reaszki.pocketin.config_module.NBT
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun PocketWand(): ItemStack {
    var item = ItemStack(Material.STICK)
    item = NBT.setDisplayName(item, "「 Area Wand 」")
    item = NBT.setLore(item, listOf("§8Left click §7pos1§8.", "§8Right click §7pos2§8."))
    NBT.createPocketInCompound(item)

    return item
}

class WandConsumeCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (!NBT.lookForCompound(sender.inventory.itemInMainHand, "pocketin")) return false
        if (args.isEmpty()) return false
        PocketArea.createArea(args[0], sender.world.name, NBT.retriveWandPos(sender.inventory.itemInMainHand,"pos1"), NBT.retriveWandPos(sender.inventory.itemInMainHand,"pos2"))
        sender.inventory.itemInMainHand.amount = 0
        sender.sendMessage("§aArea created (${args[0]})!")

        return true
    }
}