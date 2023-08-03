package com.plugin.homes.commands;

import com.plugin.homes.HomesPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public class SetHome implements CommandExecutor {

private final HomesPlugin plugin;

    public SetHome(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int MAX_HOMES = plugin.getMaxHomes();
        String maxHomesMessage = plugin.getMaxHomesMessage();
        String homeSetMessage = plugin.getHomeSetMessage();
        String homeAlreadyExistsMessage = plugin.getHomeAlreadyExistsMessage();
        String setHomeUsage = plugin.getSetHomeUseMessage();
        Boolean setHomeSound = plugin.isAllowHomeSetSound();

        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + setHomeUsage);
        }else{
            Player player = (Player) sender;
            Location location = player.getLocation();

            //Comprobamos si existe el home
            if (plugin.getConfig().get(player.getName() + "." + args[0]) != null){
                homeAlreadyExistsMessage = homeAlreadyExistsMessage.replace("%name%", args[0]);

                player.sendMessage(ChatColor.RED + homeAlreadyExistsMessage);
                return true;
            }

            //Comprobamos si existe la seccion
            if (plugin.getConfig().getConfigurationSection(player.getName()) == null){
                plugin.getConfig().createSection(player.getName());
            }

            //Comparar si el jugador tiene el maximo de homes
            if (plugin.getConfig().getConfigurationSection(player.getName()).getKeys(false).size() >= MAX_HOMES && MAX_HOMES != -1){
                player.sendMessage(ChatColor.RED + maxHomesMessage);
                return true;
            }

            plugin.getConfig().set(player.getName() + "." + args[0], location);

            plugin.saveConfig();

            homeSetMessage = homeSetMessage.replace("%name%", args[0]);

            player.sendMessage(ChatColor.GREEN + homeSetMessage);

            if (setHomeSound) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        }
        return true;
    }
}
