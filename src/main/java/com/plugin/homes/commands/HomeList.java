package com.plugin.homes.commands;

import com.plugin.homes.HomesPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public class HomeList implements CommandExecutor {

    private final HomesPlugin plugin;

    public HomeList(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        try {
            Set<String> homes = plugin.getConfig().getConfigurationSection(player.getName()).getKeys(false);

            if (homes.size() == 0){
                player.sendMessage(ChatColor.RED + "No tienes homes");
            }else{
                player.sendMessage(" ");
                player.sendMessage( ChatColor.BOLD + " Tienes " + homes.size() + " homes");

                player.sendMessage(ChatColor.YELLOW + "--------------------");
                for (String home : homes){
                    player.sendMessage(home);
                }

                player.sendMessage(ChatColor.YELLOW + "--------------------");
            }
        }catch (NullPointerException e){
            player.sendMessage("No tienes homes");
            return true;
        }
        return true;
    }
}
