package me.scholtes.mobrush;

import me.scholtes.mobrush.game.mobrushgame.MobRushGame;
import me.scholtes.mobrush.kits.manager.KitManager;
import me.scholtes.mobrush.lobby.Lobby;
import me.scholtes.mobrush.lobby.listener.LobbyListener;
import me.scholtes.mobrush.data.dao.GamePlayerDao;
import me.scholtes.mobrush.data.handler.MySQLDataHandler;
import me.scholtes.mobrush.game.player.GamePlayer;
import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class MobRushPlugin extends JavaPlugin {

    private static MobRushPlugin instance;
    private KitManager kitManager;
    private MySQLDataHandler mysqlDataHandler;

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
        mysqlDataHandler = new MySQLDataHandler(this);
      
        mobRushGame = new MobRushGame(this);
        lobby = new Lobby(this, mobRushGame);

        saveDefaultConfig();
        registerCommands();
        registerListeners();
      
        Bukkit.getScheduler().runTaskAsynchronously(this, () ->
                mysqlDataHandler.getJdbi().useExtension(GamePlayerDao.class, GamePlayerDao::createTable));
    }

    @Override
    public void onDisable() {
        mysqlDataHandler.close();
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
