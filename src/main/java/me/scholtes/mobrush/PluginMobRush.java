package me.scholtes.mobrush;

import org.bukkit.plugin.java.JavaPlugin;

public final class PluginMobRush extends JavaPlugin {

    private static PluginMobRush instance;

    /**
     * Enables the plugin and registers commands and listeners
     * TODO: Initiliaze database, storage, lobby etc.
     */
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Registers the commands
     */
    private  void registerCommands() {

    }

    /**
     * Registers the listeners
     */
    private void registerListeners() {

    }

    public static PluginMobRush getInstance() {
        return instance;
    }
}
