package com.plugin.homes;

import com.plugin.homes.commands.DelHome;
import com.plugin.homes.commands.Home;
import com.plugin.homes.commands.HomeList;
import com.plugin.homes.commands.SetHome;
import com.plugin.homes.enumerators.Language;
import com.plugin.homes.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomesPlugin extends JavaPlugin {

    private static HomesPlugin instance;

    private Language language; //Language of the plugin

    private boolean allowEnabledMessage; //Boolean that will determine if the plugin will show a message when it is enabled
    private boolean allowWelcomeMessage; //Boolean that will determine if the plugin will show a message when a player joins the server
    private boolean allowDefaultWelcomeMessage; //Boolean that will determine if the plugin will show the default message when a player joins the server



    private String enabledMessage; //Message that will pop up in the console when the plugin is enabled
    private String welcomeMessage; //Mesagge that will pop up in the console when a player joins the server
    private String maxHomesMessage; //Message that will pop up when a player tries to set a home and he has reached the maximum number of homes
    private String homeSetMessage; //Message that will pop up when a player sets a home


    private int maxHomes; //Maximum number of homes a player can have

    @Override
    public void onEnable() {
        // Register the listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        // Register the commands
        getCommand("sethome").setExecutor(new SetHome(this));
        getCommand("home").setExecutor(new Home(this));
        getCommand("delhome").setExecutor(new DelHome(this));
        getCommand("homeList").setExecutor(new HomeList(this));

        instance = this;

        saveDefaultConfig(); //Save the default configuration

        //Get the language from the config
        language = Language.getLanguage(getConfig().getString("language"));

        if (language == null){
            System.out.println("The language you have selected is not available, the plugin will use the default language (english)");
            language = Language.ENGLISH;
        }

        //Get the messages from the config based on the language

        welcomeMessage = getConfig().getConfigurationSection(language.getLanguage()).getString("welcome_message");
        enabledMessage = getConfig().getConfigurationSection(language.getLanguage()).getString("enabled_message");
        maxHomesMessage = getConfig().getConfigurationSection(language.getLanguage()).getString("max_homes_message");
        homeSetMessage = getConfig().getConfigurationSection(language.getLanguage()).getString("home_set_message");

        allowEnabledMessage = getConfig().getBoolean("allow_enabled_message");
        allowWelcomeMessage = getConfig().getBoolean("allow_welcome_message");
        allowDefaultWelcomeMessage = getConfig().getBoolean("allow_default_welcome_message");

        maxHomes = getConfig().getInt("max_homes");

        if (allowEnabledMessage) {
            System.out.println(enabledMessage);
        }
    }


    //region Getters

    public static HomesPlugin getInstance() {
        return instance;
    }

    public Language getLanguage() {
        return language;
    }

    public boolean isAllowEnabledMessage() {
        return allowEnabledMessage;
    }

    public boolean isAllowWelcomeMessage() {
        return allowWelcomeMessage;
    }

    public String getEnabledMessage() {
        return enabledMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public boolean isAllowDefaultWelcomeMessage() {
        return allowDefaultWelcomeMessage;
    }

    public int getMaxHomes() {
        return maxHomes;
    }

    public String getMaxHomesMessage() {
        return maxHomesMessage;
    }

    public String getHomeSetMessage() {
        return homeSetMessage;
    }

    //endregion
}
