package com.arwenmc.commands;

import com.arwenmc.CoreSys;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("gamemode")){
            if (!sender.hasPermission("gamemode")) {
                sender.sendMessage("You do not have permission!");
                return true;
            }
            else if (args.length == 1) {
                if(args[0].equalsIgnoreCase("creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                }

            }
        }
        return true;
    }
}
