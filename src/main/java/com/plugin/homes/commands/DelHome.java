package com.plugin.homes.commands;

import com.plugin.homes.HomesPlugin;
import org.bukkit.ChatColor;
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
public class DelHome implements CommandExecutor {

    private final HomesPlugin plugin;

    public DelHome(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String homeDoesNotExistMessage = plugin.getHomeDoesNotExistMessage();
        String delHomeUseMessage = plugin.getDelHomeUseMessage();
        String homeDeletedMessage = plugin.getHomeDeletedMessage();
        Boolean allowDelHomeSound = plugin.isAllowDeletedSound();

        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + delHomeUseMessage);
        }else{
            Player player = (Player) sender;

            //Check if home exists
            if (plugin.getConfig().get(player.getName() + "." + args[0]) == null){
                homeDoesNotExistMessage = homeDoesNotExistMessage.replace("%name%", args[0]);
                player.sendMessage(ChatColor.RED + homeDoesNotExistMessage);
                return true;
            }

            plugin.getConfig().set(player.getName() + "." + args[0], null);

            plugin.saveConfig();

            homeDeletedMessage = homeDeletedMessage.replace("%name%", args[0]);
            player.sendMessage(ChatColor.GREEN + homeDeletedMessage);

            if (allowDelHomeSound){
                player.playSound(player, Sound.ENTITY_PLAYER_DEATH, 1, 1);
            }
        }
        return true;
    }
}
