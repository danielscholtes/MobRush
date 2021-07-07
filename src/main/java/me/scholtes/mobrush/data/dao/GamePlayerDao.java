package me.scholtes.mobrush.data.dao;

import me.scholtes.mobrush.game.player.GamePlayer;
import me.scholtes.mobrush.kits.KitType;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface GamePlayerDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS players (" +
                    "uuid VARCHAR(36) NOT NULL UNIQUE," +
                    "points INT NOT NULL," +
                    "kit VARCHAR(16) NOT NULL," +
                    "PRIMARY KEY (uuid)" +
                ")")
    void createTable();

    @SqlUpdate("INSERT INTO players (uuid, points, kit) VALUES (:uuid, :points, :kit)" +
                "ON DUPLICATE KEY UPDATE points = :points, kit = :kit")
    void insertPlayer(@BindBean GamePlayer gamePlayer);

    @SqlQuery("SELECT * FROM players WHERE uuid = :uuid")
    @RegisterConstructorMapper(GamePlayer.class)
    Optional<GamePlayer> getGamePlayer(@Bind("uuid") String uuid);

}
