package com.arwenmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;

public class PlayerHandler {

    public static int Owner = 100;
    public static int Admin = 80;
    public static int Moderator = 60;
    public static int Helper = 50;
    public static int Member = 0;

    public void SetupPlayer(Player player) {
        File file = new File("plugins/CoreSystem/PlayerData/" + player.getUniqueId() + ".yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Error when creating file: " + e.getMessage());
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        yml.addDefault("Name", player.getName());
        yml.addDefault("UUID", player.getUniqueId());
        yml.addDefault("Rank", Member);
        yml.options().copyDefaults(true);

        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean setRank(Player player, int rank){
        File file = new File("plugins/CoreSystem/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        yml.set("Rank", rank);

        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getRank(Player player) {
        File file = new File("plugins/CoreSystem/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
                return yml.getInt("Rank");
    }

    public String getRankPrefix(int Rank){
        if(Rank == Owner) {
            return ChatColor.RED.toString() + ChatColor.BOLD + "Owner " + ChatColor.WHITE;
        }else if(Rank == Admin) {
            return ChatColor.AQUA.toString() + ChatColor.BOLD + "Admin " + ChatColor.WHITE;
        }else if(Rank == Moderator) {
            return ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Mod " + ChatColor.WHITE;
        }else if(Rank == Helper) {
            return ChatColor.GREEN.toString() + ChatColor.BOLD + "Helper " + ChatColor.WHITE;
        }else{
            return "";
        }
    }
    public void refreshRanks() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                String prefix = getRankPrefix(getRank(player1));
                Team team = board.registerNewTeam(player1.getName());
                team.setPrefix(prefix);
                team.addEntry(player1.getName());

            }
            player.setScoreboard(board);
        }
    }
}
