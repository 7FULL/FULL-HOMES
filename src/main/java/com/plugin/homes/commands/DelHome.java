package com.plugin.homes.commands;

import com.plugin.homes.HomesPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public class DelHome implements CommandExecutor {

    private final HomesPlugin plugin;

    public DelHome(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("Uso: /delhome <nombre>");
        }else{
            Player player = (Player) sender;

            //Comprobamos si existe el home
            if (plugin.getConfig().get(player.getName() + "." + args[0]) == null){
                player.sendMessage(ChatColor.RED +"Home " + args[0] + " no existe");
                return true;
            }

            plugin.getConfig().set(player.getName() + "." + args[0], null);

            plugin.saveConfig();

            player.sendMessage("Home " + args[0] + " eliminado");
        }
        return true;
    }
}
