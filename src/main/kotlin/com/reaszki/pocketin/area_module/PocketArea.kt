package com.reaszki.pocketin.area_module

import com.google.gson.Gson
import com.reaszki.pocketin.config_module.Files
import com.reaszki.pocketin.config_module.Plugin
import com.reaszki.pocketin.config_module.Registry
import org.bukkit.Location
import java.io.File

object PocketArea {

    var loadedAreas = mutableListOf<PocketRegion>()

    fun createArea(
        name: String,
        world: String,
        pos1: List<Long>,
        pos2: List<Long>
    ) {
        var area = PocketRegion(
            name,
            world,
            mutableListOf(pos1[0].toDouble(), pos1[1].toDouble(), pos1[2].toDouble()),
            mutableListOf(pos2[0].toDouble(), pos2[1].toDouble(), pos2[2].toDouble())
        )

        saveArea(area)
    }

    fun saveArea(area: PocketRegion) {
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
            val area1X = minOf(area.pos1[0], area.pos2[0]).toInt()
            val area1Y = minOf(area.pos1[1], area.pos2[1]).toInt()
            val area1Z = minOf(area.pos1[2], area.pos2[2]).toInt()

            val area2X = maxOf(area.pos1[0], area.pos2[0]).toInt()
            val area2Y = maxOf(area.pos1[1], area.pos2[1]).toInt()
            val area2Z = maxOf(area.pos1[2], area.pos2[2]).toInt()

            if (
                areaX >= area1X && areaX <= area2X &&
                areaY >= area1Y && areaY <= area2Y &&
                areaZ >= area1Z && areaZ <= area2Z
            ) {
                listOfAreas.add(area)
            }
        }
        return listOfAreas
    }
}