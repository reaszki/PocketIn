package com.reaszki.pocketin.area_module

import com.reaszki.pocketin.config_module.NBT
import de.tr7zw.nbtapi.NBTType
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

object PocketWand {
    fun item(): ItemStack {
        var item = ItemStack(Material.STICK)
        item = NBT.setDisplayName(item, "「 Area Wand 」")
        item = NBT.setLore(item, listOf("§8Left click §7pos1§8.", "§8Right click §7pos2§8."))
        NBT.createPocketInCompound(item)

        return item
    }

    // TODO: ARRUMAR BAGUNÇA COM ARRAY
    fun PocketWandSelection(event: PlayerInteractEvent) {
        var hand = event.player.inventory.itemInMainHand

        if (hand.type == Material.AIR) return

        var correctItem = false
        de.tr7zw.nbtapi.NBT.get(hand) { nbt ->
            if (nbt.hasTag("pocketin", NBTType.NBTTagCompound)) correctItem = true
        }
        if (!correctItem) return
        when (event.action) {
            Action.LEFT_CLICK_BLOCK -> {
                var pos1 = event.clickedBlock
                var arr = intArrayOf(pos1!!.x, pos1!!.y, pos1!!.z)
                de.tr7zw.nbtapi.NBT.modify(hand) { nbt ->
                    nbt.getCompound("pocketin")?.setIntArray("pos1", arr)
                }
            }
            Action.RIGHT_CLICK_BLOCK -> {
                if (event.hand != EquipmentSlot.HAND) return
                var pos2 = event.clickedBlock
                var arr = intArrayOf(pos2!!.x, pos2!!.y, pos2!!.z)
                de.tr7zw.nbtapi.NBT.modify(hand) { nbt ->
                    nbt.getCompound("pocketin")?.setIntArray("pos2", arr)
                }
            }
            else -> {}
        }
        event.isCancelled = true
    }
}