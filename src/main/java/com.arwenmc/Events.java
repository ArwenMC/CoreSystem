package com.arwenmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    PlayerHandler PlayerHandler;
    CoreSys plugin;

    public Events(PlayerHandler _PlayerHandler) {
        PlayerHandler = _PlayerHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerHandler.SetupPlayer(player);
        PlayerHandler.refreshRanks();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String name = player.getName();
        String prefix = PlayerHandler.getRankPrefix(PlayerHandler.getRank(player));
        String message = event.getMessage();
        Bukkit.broadcastMessage(prefix + name + ": " + message);
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "").toLowerCase();
        int rank = PlayerHandler.getRank(player);
        if (cmd.equals("rank")) {
            if (rank >= PlayerHandler.Admin) {
                event.setCancelled(true);
                if (args.length == 3) {
                    String targetName = args[1];
                    Player target = Bukkit.getPlayer(targetName);
                    if (target != null) {
                        int rankValue = 0;
                        String rankName = args[2].toLowerCase();
                        if (rankName.equals("owner")) {
                            rankValue = PlayerHandler.Owner;
                        } else if (rankName.equals("admin")) {
                            rankValue = PlayerHandler.Admin;

                        } else if (rankName.equals("moderator")) {
                            rankValue = PlayerHandler.Moderator;
                        } else if (rankName.equals("member")) {
                            rankValue = PlayerHandler.Member;
                        } else {
                            rankValue = -1;
                        }
                        if (rankValue >= 0) {
                            if (rankValue < rank) {
                                if (PlayerHandler.getRank(target) < rank) {
                                    if (PlayerHandler.setRank(target, rankValue)) {
                                        player.sendMessage(ChatColor.GREEN + "Successfully set " + target.getName() + "'s rank to " + rankName);
                                        target.sendMessage(ChatColor.GREEN + "Your rank has been changed to " + rankName);
                                        PlayerHandler.refreshRanks();
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Error: You can't modify people who are the same or a higher rank than you!");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "Error: You can't use ranks bigger than or equal to yours!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Error: " + rankName + " is not a rank!");
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "Error: " + targetName + " is not online!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /rank <player> <rank>");
                }
            }
        }
    }
}