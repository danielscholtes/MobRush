package me.scholtes.mobrush.data.storage;

import me.scholtes.mobrush.data.dao.GamePlayerDao;
import me.scholtes.mobrush.game.player.GamePlayer;
import me.scholtes.mobrush.kits.KitType;
import me.scholtes.mobrush.utils.AsyncHelper;
import org.jdbi.v3.core.Jdbi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class DataStorage {

    private final Jdbi jdbi;
    private final AsyncHelper asyncHelper;

    private Map<UUID, GamePlayer> players;

    public DataStorage(AsyncHelper asyncHelper, Jdbi jdbi) {
        this.jdbi = jdbi;
        this.asyncHelper = asyncHelper;
        players = new HashMap<>();
    }

    /**
     * Loads the player data from the database
     */
    public void loadPlayer(UUID uuid) {
        asyncHelper.runTask(() -> {
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
        asyncHelper.runTask(() -> jdbi.useExtension(GamePlayerDao.class, dao -> dao.insertPlayer(players.get(uuid))));
    }

    /**
     * Gets the GamePlayer of the UUID
     */
    public GamePlayer getGamePlayer(UUID uuid) {
        return players.get(uuid);
    }

    /**
     * Lets you use a consumer for the GamePlayer
     * Still need to manually save
     */
    public void useGamePlayer(UUID uuid, Consumer<GamePlayer> consumer) {
        consumer.accept(players.get(uuid));
    }

}