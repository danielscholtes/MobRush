package me.scholtes.mobrush;

import me.scholtes.mobrush.game.task.GameTaskManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobRushPlugin extends JavaPlugin {

    private static MobRushPlugin instance;

    private GameTaskManager taskManager;

    /**
     * Enables the plugin and registers commands and listeners
     * TODO: Initiliaze database, storage, lobby etc.
     */
    @Override
    public void onEnable() {
        instance = this;

        taskManager = new GameTaskManager(this);

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

    public static MobRushPlugin getInstance() {
        return instance;
    }
}
