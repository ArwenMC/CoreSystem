package com.arwenmc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CoreSys extends JavaPlugin implements Listener {

    /*
        Main Plugin Class For CoreSystem
     */

    public CoreSys plugin = this;

    FileHandler FileHandler = new FileHandler();
    PlayerHandler PlayerHandler = new PlayerHandler();
    Events Events = new Events(PlayerHandler);



    @Override
    public void onEnable() {
        FileHandler.Setup();
        getServer().getPluginManager().registerEvents(Events,this);

    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Plugin Disabled");

    }


}
