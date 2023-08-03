package com.plugin.homes.listeners;

import com.plugin.homes.HomesPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public class PlayerListener implements Listener {

    private final HomesPlugin plugin;

    public PlayerListener(HomesPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if (plugin.isAllowWelcomeMessage()){
            event.getPlayer().sendMessage(plugin.getWelcomeMessage());
        }
        if (!plugin.isAllowDefaultWelcomeMessage()){
            event.setJoinMessage("");
        }
    }
}
