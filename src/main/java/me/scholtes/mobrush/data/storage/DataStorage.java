package me.scholtes.mobrush.data.storage;

import me.scholtes.mobrush.data.dao.GamePlayerDao;
import me.scholtes.mobrush.game.player.GamePlayer;
import me.scholtes.mobrush.kits.KitType;
import me.scholtes.mobrush.utils.AsyncUtil;
import org.bukkit.Bukkit;
import org.jdbi.v3.core.Jdbi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class DataStorage {

    private final Jdbi jdbi;
    private final AsyncUtil asyncUtil;

    private Map<UUID, GamePlayer> players;

    public DataStorage(AsyncUtil asyncUtil, Jdbi jdbi) {
        this.jdbi = jdbi;
        this.asyncUtil = asyncUtil;
        players = new HashMap<>();
    }

    /**
     * Loads the player data from the database
     */
    public void loadPlayer(UUID uuid) {
        asyncUtil.runTask(() -> {
            Optional<GamePlayer> gamePlayerOptional = jdbi.withExtension(GamePlayerDao.class,
                    dao -> dao.getGamePlayer(uuid.toString()));

            if (!gamePlayerOptional.isPresent()) {
                GamePlayer gamePlayer = new GamePlayer(uuid, 0, KitType.KNIGHT);
                players.put(uuid, gamePlayer);
                return;
            }

            players.put(uuid, gamePlayerOptional.get());
        });
    }

    /**
     * Removes the player from the cache
     */
    public void unloadPlayer(UUID uuid) {
        players.remove(uuid);
    }

    /**
     * Saves the player to the database
     */
    public void savePlayer(UUID uuid) {
        asyncUtil.runTask(() -> jdbi.useExtension(GamePlayerDao.class, dao -> dao.insertPlayer(players.get(uuid))));
    }

    /**
     * Gets the players current points
     */
    public int getPoints(UUID uuid) {
        return players.get(uuid).getPoints();
    }

    /**
     * Adds points to the player
     */
    public void addPoints(UUID uuid, int points) {
        players.get(uuid).addPoints(points);
        savePlayer(uuid);
    }

    /**
     * Removes points from the player
     */
    public void removePoints(UUID uuid, int points) {
        players.get(uuid).addPoints(points);
        savePlayer(uuid);
    }

    /**
     * Returns the currently selected kit of the player
     */
    public KitType getKit(UUID uuid, KitType kit) {
        return players.get(uuid).getKit();
    }

    /**
     * Returns the currently selected kit of the player
     */
    public void setKit(UUID uuid, KitType kit) {
        players.get(uuid).setKit(kit);
        savePlayer(uuid);
    }

}