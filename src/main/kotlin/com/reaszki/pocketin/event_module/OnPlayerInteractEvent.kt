package com.reaszki.pocketin.event_module

import com.reaszki.pocketin.area_module.PocketWand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class OnPlayerInteractEvent: Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        PocketWand.PocketWandSelection(event)
    }
}