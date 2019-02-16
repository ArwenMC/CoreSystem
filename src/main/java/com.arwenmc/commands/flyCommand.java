package com.arwenmc.commands;

import com.arwenmc.CoreSys;
import com.arwenmc.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class flyCommand implements CommandExecutor {
    CoreSys plugin;

    public boolean onCommand(org.bukkit.command.CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        int rank = PlayerHandler.getRank(p);
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (p.hasPermission("fly.use") | rank >= PlayerHandler.Moderator) {
                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage(ChatColor.AQUA + "Fly:" + ChatColor.DARK_RED + " DISABLED");
                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.0F);
                    return true;
                }
                if (!p.getAllowFlight()) {
                    p.setAllowFlight(true);
                    p.sendMessage(ChatColor.AQUA + "Fly:" + ChatColor.GREEN + " ENABLED");
                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.0F);
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to run this command!"); //plugin.NO_PERMISSION.toString());
            }
        }
        return true;
    }
}


