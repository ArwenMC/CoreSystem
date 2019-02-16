package com.arwenmc;

import com.arwenmc.commands.flyCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CoreSys extends JavaPlugin implements Listener {

    /*
        Main Plugin Class For CoreSystem
        TEst
     */

    public CoreSys plugin = this;
    FileHandler FileHandler = new FileHandler();
    PlayerHandler PlayerHandler = new PlayerHandler();
    Events Events = new Events(PlayerHandler);
    public PluginDescriptionFile pdfFile = this.getDescription();


    @Override
    public void onEnable() {
        FileHandler.Setup();
        getServer().getPluginManager().registerEvents(Events,this);
        this.getCommand("fly").setExecutor(new flyCommand());
        getLogger().info("CoreSystem v" + pdfFile.getVersion() + " has been enabled.");


    }

    @Override
    public void onDisable() {
        getLogger().info("CoreSystem v" + pdfFile.getVersion() + " has been disabled.");

    }


}
