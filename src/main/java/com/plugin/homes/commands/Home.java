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
public class Home implements CommandExecutor {

    private final HomesPlugin plugin;

    public Home(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String homeUseMessage = plugin.getHomeUseMessage();
        String homeNoExistMessage = plugin.getHomeDoesNotExistMessage();
        Boolean allowTeleportMessage = plugin.isAllowTeleportMessage();
        String teleportMessage = plugin.getTeleportMessage();
        Boolean allowTelportSound = plugin.isAllowTeleportSound();

        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + homeUseMessage);
        }else{
            Player player = (Player) sender;

            Location location = plugin.getConfig().getLocation(sender.getName() + "." + args[0]);

            if (location != null){
                player.teleport(location);

                if (allowTeleportMessage){
                    teleportMessage = teleportMessage.replace("%name%", args[0]);
                    player.sendMessage(ChatColor.GREEN + teleportMessage);

                    if (allowTelportSound){
                        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            }else{
                homeNoExistMessage = homeNoExistMessage.replace("%name%", args[0]);
                player.sendMessage( ChatColor.RED + homeNoExistMessage);
            }
        }
        return true;
    }
}
