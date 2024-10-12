package com.reaszki.pocketin.area_module

import com.google.gson.Gson
import com.reaszki.pocketin.config_module.Files
import com.reaszki.pocketin.config_module.Plugin
import com.reaszki.pocketin.config_module.Registry
import org.bukkit.Location
import java.io.File

object PocketArea {

    var loadedAreas = mutableListOf<PocketRegion>()

    fun createArea(name: String, world: String, pos1: List<Int>, pos2: List<Int>) {
        var area = PocketRegion(
            name,
            world,
            pos1,
            pos2
        )

        saveArea(area)
    }

    private fun saveArea(area: PocketRegion) {
        if (Files.lookForSubFolder(Plugin.plugin, "areas")) {
            var pocketRegionJson = Gson().toJson(area)
            var file = File(Files.returnFolder(Plugin.plugin, "areas"), "${area.name}.json")

            file.createNewFile()
            file.writeText(pocketRegionJson)

            loadAreas()
        }
    }

    fun loadAreas() {
        if (Files.lookForSubFolder(Plugin.plugin, "areas")) {
            var folder = Files.returnFolder(Plugin.plugin, "areas")

            folder.listFiles().forEach { file ->
                loadedAreas.add(Gson().fromJson(file.readText(), PocketRegion::class.java))
            }
        }
    }

    fun lookUpArea(pos: Location): MutableList<PocketRegion?> {
        val listOfAreas = mutableListOf<PocketRegion?>()

        val areaX = pos.x.toInt()
        val areaY = pos.y.toInt()
        val areaZ = pos.z.toInt()

        loadedAreas.forEach { area ->
            val area1X = minOf(area.pos1[0], area.pos2[0])
            val area1Y = minOf(area.pos1[1], area.pos2[1])
            val area1Z = minOf(area.pos1[2], area.pos2[2])

            val area2X = maxOf(area.pos1[0], area.pos2[0])
            val area2Y = maxOf(area.pos1[1], area.pos2[1])
            val area2Z = maxOf(area.pos1[2], area.pos2[2])

            if (
                areaX in (area1X..area2X) &&
                areaY in (area1Y..area2Y) &&
                areaZ in (area1Z..area2Z)
            ) {
                listOfAreas.add(area)
            }
        }
        return listOfAreas
    }
}