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
public class Home implements CommandExecutor {

    private final HomesPlugin plugin;

    public Home(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("Uso: /home <nombre>");
        }else{
            Player player = (Player) sender;

            Location location = plugin.getConfig().getLocation(sender.getName() + "." + args[0]);

            if (location != null){
                player.teleport(location);
            }else{
                player.sendMessage( ChatColor.RED + "Home " + args[0] + " no existe");
            }
        }
        return true;
    }
}
