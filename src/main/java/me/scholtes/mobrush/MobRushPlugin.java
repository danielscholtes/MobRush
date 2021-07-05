package me.scholtes.mobrush;

import me.scholtes.mobrush.game.mobrushgame.MobRushGame;
import me.scholtes.mobrush.kits.manager.KitManager;
import me.scholtes.mobrush.lobby.Lobby;
import me.scholtes.mobrush.lobby.listener.LobbyListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobRushPlugin extends JavaPlugin {

    private static MobRushPlugin instance;
    private KitManager kitManager;

    private MobRushGame mobRushGame;
    private Lobby lobby;

    /**
     * Enables the plugin and registers commands and listeners
     * TODO: Initiliaze database, storage, lobby etc.
     */
    @Override
    public void onEnable() {
        instance = this;
        kitManager = new KitManager();

        saveDefaultConfig();
        registerCommands();
        registerListeners();

        mobRushGame = new MobRushGame(this);
        lobby = new Lobby(this, mobRushGame);
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
        //Lobby Listener
        getServer().getPluginManager().registerEvents(new LobbyListener(lobby), this);
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
