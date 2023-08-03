package com.plugin.homes.commands;

import com.plugin.homes.HomesPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

private int MAX_HOMES = 3;

private String maxHomesMessage = "If you see this message, please contact the server administrator.";

private String homeSetMessage = "If you see this message, please contact the server administrator.";

    public SetHome(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        MAX_HOMES = plugin.getMaxHomes();
        maxHomesMessage = plugin.getMaxHomesMessage();
        homeSetMessage = plugin.getHomeSetMessage();

        if (args.length == 0){
            sender.sendMessage("Uso: /sethome <nombre>");
        }else{
            Player player = (Player) sender;
            Location location = player.getLocation();

            //Comprobamos si existe el home
            if (plugin.getConfig().get(player.getName() + "." + args[0]) != null){
                player.sendMessage(ChatColor.RED +"Home " + args[0] + " ya existe");
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

            player.sendMessage(homeSetMessage);
        }
        return true;
    }
}
