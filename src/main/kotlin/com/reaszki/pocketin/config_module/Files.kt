package com.reaszki.pocketin.config_module

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object Files {
    fun lookForConfigFolder(plugin: JavaPlugin): Boolean {
        return plugin.dataFolder.absoluteFile.exists()
    }

    fun createConfigFolder(plugin: JavaPlugin): Boolean {
        if (lookForConfigFolder(plugin)) return false
        else plugin.dataFolder.mkdir(); return true
    }

    fun lookForSubFolder(plugin: JavaPlugin, subFolder: String): Boolean {
        var path = File(plugin.dataFolder.absolutePath, subFolder)
        return path.exists()
    }

    fun createSubFolder(plugin: JavaPlugin, subFolder: String): Boolean {
        var path = File(plugin.dataFolder.absolutePath, subFolder)
        if (path.exists()) return false
        else path.mkdir(); return true
    }

    fun returnFolder(plugin: JavaPlugin, subFolder: String): File {
        var path = File(plugin.dataFolder.absolutePath, subFolder)
        return path
    }
}