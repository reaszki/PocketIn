package com.reaszki.pocketin.config_module

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.NBTType
import org.bukkit.inventory.ItemStack

object NBT {
    fun lookForCompound(item: ItemStack, compoundName: String): Boolean {
        var control = false
        NBT.get(item) { nbt ->
            if (nbt.hasTag(compoundName, NBTType.NBTTagCompound)) control = true
        }
        return control
    }

    fun createPocketInCompound(item: ItemStack) {
        NBT.modify(item) { nbt ->
            nbt.getOrCreateCompound("pocketin")
        }
    }

    fun setDisplayName(item: ItemStack, name: String): ItemStack {
        var meta = item.itemMeta
        meta!!.setDisplayName("§r$name")
        item.itemMeta = meta
        return item
    }

    fun setLore(item: ItemStack, lore: List<String>): ItemStack {
        var meta = item.itemMeta
        meta!!.lore = lore
        item.itemMeta = meta
        return item
    }

    fun retriveWandPos(item: ItemStack, pos: String): List<Int> {
        lateinit var arr: List<Int>
        NBT.get(item) { nbt ->
            arr = nbt.getCompound("pocketin")?.getIntArray(pos)!!.asList()
        }
        return arr
    }
}