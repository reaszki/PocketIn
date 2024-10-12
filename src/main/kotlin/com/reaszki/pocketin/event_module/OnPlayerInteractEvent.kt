package com.reaszki.pocketin.event_module

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.NBTType
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class OnPlayerInteractEvent: Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        var hand = event.player.inventory.itemInMainHand

        if (hand.type == Material.AIR) return
        var correctItem = false
        NBT.get(hand) { nbt ->
            if (nbt.hasTag("pocketin", NBTType.NBTTagCompound)) correctItem = true
        }
        if (!correctItem) return
        when (event.action) {
            Action.LEFT_CLICK_BLOCK -> {
                var pos1 = event.clickedBlock
                var arr = longArrayOf(pos1!!.x.toLong(), pos1!!.y.toLong(), pos1!!.z.toLong())
                NBT.modify(hand) { nbt ->
                    nbt.getCompound("pocketin")?.setLongArray("pos1", arr)
                }
            }
            Action.RIGHT_CLICK_BLOCK -> {
                if (event.hand != EquipmentSlot.HAND) return
                var pos2 = event.clickedBlock
                var arr = longArrayOf(pos2!!.x.toLong(), pos2!!.y.toLong(), pos2!!.z.toLong())
                NBT.modify(hand) { nbt ->
                    nbt.getCompound("pocketin")?.setLongArray("pos2", arr)
                }
            }
            else -> {}
        }
        event.isCancelled = true
    }
}