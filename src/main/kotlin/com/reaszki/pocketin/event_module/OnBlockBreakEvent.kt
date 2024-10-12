package com.reaszki.pocketin.event_module

import com.reaszki.pocketin.area_module.PocketWand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class OnBlockBreakEvent: Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        event.player.inventory.addItem(PocketWand())
    }
}