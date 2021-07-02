package me.scholtes.mobrush;

import me.scholtes.mobrush.data.handler.MySQLDataHandler;
import me.scholtes.mobrush.kits.manager.KitManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobRushPlugin extends JavaPlugin {

    private static MobRushPlugin instance;
    private KitManager kitManager;
    private MySQLDataHandler mySQLDataHandler;

    /**
     * Enables the plugin and registers commands and listeners
     * TODO: Initiliaze database, storage, lobby etc.
     */
    @Override
    public void onEnable() {
        instance = this;
        kitManager = new KitManager();
        mySQLDataHandler = new MySQLDataHandler(this);

        saveDefaultConfig();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
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

    /**
     * Gets the {@link KitManager kit manager}
     *
     * @return Instance of the {@link KitManager kit manager}
     */
    public KitManager getKitManager() {
        return kitManager;
    }

    public static MobRushPlugin getInstance() {
        return instance;
    }
}
