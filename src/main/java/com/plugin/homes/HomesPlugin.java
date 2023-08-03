package com.plugin.homes;

import com.plugin.homes.commands.DelHome;
import com.plugin.homes.commands.Home;
import com.plugin.homes.commands.HomeList;
import com.plugin.homes.commands.SetHome;
import com.plugin.homes.enumerators.Language;
import com.plugin.homes.listeners.PlayerListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomesPlugin extends JavaPlugin {

    private static HomesPlugin instance;

    private Language language; //Language of the plugin

    private boolean allowEnabledMessage; //Boolean that will determine if the plugin will show a message when it is enabled
    private boolean allowWelcomeMessage; //Boolean that will determine if the plugin will show a message when a player joins the server
    private boolean allowDefaultWelcomeMessage; //Boolean that will determine if the plugin will show the default message when a player joins the server
    private boolean allowTeleportMessage; //Boolean that will determine if the plugin will show a message when a player teleports to a home
    private boolean allowTeleportSound; //Boolean that will determine if the plugin will play a sound when a player teleports to a home
    private boolean allowHomeSetSound; //Boolean that will determine if the plugin will play a sound when a player sets a home
    private boolean allowDeletedSound; //Boolean that will determine if the plugin will play a sound when a player deletes a home



    private String enabledMessage; //Message that will pop up in the console when the plugin is enabled
    private String welcomeMessage; //Mesagge that will pop up in the console when a player joins the server
    private String maxHomesMessage; //Message that will pop up when a player tries to set a home and he has reached the maximum number of homes
    private String homeSetMessage; //Message that will pop up when a player sets a home
    private String homeAlreadyExistsMessage; //Message that will pop up when a player tries to set a home and he already has a home with that name
    private String setHomeUseMessage; //Message that will pop up when a player uses the command /sethome incorrectly
    private String zeroHomesMessage; //Message that will pop up when a player tries to use the command /homeList and he has no homes
    private String manyHomesMessage; //Message that will pop up when a player tries to use the command /homeList and he has at least 1 home
    private String homeUseMessage; //Message that will pop up when a player uses the command /home incorrectly
    private String homeDoesNotExistMessage; //Message that will pop up when a player tries to use the command /home and he has no home with that name
    private String delHomeUseMessage; //Message that will pop up when a player uses the command /delhome incorrectly
    private String homeDeletedMessage; //Message that will pop up when a player deletes a home
    private String teleportMessage; //Message that will pop up when a player teleports to a home


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
        ConfigurationSection section = getConfig().getConfigurationSection(language.getLanguage());

        welcomeMessage = section.getString("welcome_message");
        enabledMessage = section.getString("enabled_message");
        maxHomesMessage = section.getString("max_homes_message");
        homeSetMessage = section.getString("home_set_message");
        homeAlreadyExistsMessage = section.getString("home_already_exists_message");
        setHomeUseMessage = section.getString("set_home_use_message");
        zeroHomesMessage = section.getString("zero_homes_message");
        manyHomesMessage = section.getString("many_homes_message");
        homeUseMessage = section.getString("home_use_message");
        homeDoesNotExistMessage = section.getString("home_not_exists_message");
        teleportMessage = section.getString("teleport_message");
        delHomeUseMessage = section.getString("delete_home_use_message");
        homeDeletedMessage = section.getString("delete_home_message");

        allowEnabledMessage = getConfig().getBoolean("allow_enabled_message");
        allowWelcomeMessage = getConfig().getBoolean("allow_welcome_message");
        allowDefaultWelcomeMessage = getConfig().getBoolean("allow_default_welcome_message");
        allowTeleportMessage = getConfig().getBoolean("allow_teleport_message");
        allowTeleportSound = getConfig().getBoolean("allow_teleport_sound");
        allowHomeSetSound = getConfig().getBoolean("allow_home_set_sound");
        allowDeletedSound = getConfig().getBoolean("allow_deleted_sound");

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

    public String getHomeAlreadyExistsMessage() {
        return homeAlreadyExistsMessage;
    }

    public String getSetHomeUseMessage() {
        return setHomeUseMessage;
    }

    public String getZeroHomesMessage() {
        return zeroHomesMessage;
    }

    public String getManyHomesMessage() {
        return manyHomesMessage;
    }

    public String getHomeUseMessage() {
        return homeUseMessage;
    }

    public String getHomeDoesNotExistMessage() {
        return homeDoesNotExistMessage;
    }

    public String getDelHomeUseMessage() {
        return delHomeUseMessage;
    }

    public String getHomeDeletedMessage() {
        return homeDeletedMessage;
    }

    public boolean isAllowTeleportMessage() {
        return allowTeleportMessage;
    }

    public String getTeleportMessage() {
        return teleportMessage;
    }

    public boolean isAllowTeleportSound() {
        return allowTeleportSound;
    }

    public boolean isAllowHomeSetSound() {
        return allowHomeSetSound;
    }

    public boolean isAllowDeletedSound() {
        return allowDeletedSound;
    }

    //endregion
}
