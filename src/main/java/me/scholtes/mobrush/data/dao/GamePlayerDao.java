package me.scholtes.mobrush.data.dao;

import me.scholtes.mobrush.game.player.GamePlayer;
import me.scholtes.mobrush.kits.KitType;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface GamePlayerDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS players (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "uuid VARCHAR(36) NOT NULL UNIQUE," +
                    "points INT NOT NULL," +
                    "kit VARCHAR(16) NOT NULL," +
                    "PRIMARY KEY (id)" +
                ")")
    void createTable();

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM players WHERE uuid = :uuid)")
    boolean exists(@Bind("uuid") String uuid);

    @SqlUpdate("INSERT INTO players (id, uuid, points, kit) VALUES (:id, :uuid, :points, :kit)" +
                "ON DUPLICATE KEY UPDATE points = :points, kit = :kit")
    void insertPlayer(@Bind("id") int id, @Bind("uuid") String uuid, @Bind("points") int points, @Bind("kit") KitType kit);

    @SqlUpdate("INSERT INTO players (id, uuid, points, kit) VALUES (:id, :uuid, :points, :kit)" +
            "ON DUPLICATE KEY UPDATE points = :points, kit = :kit")
    void insertPlayer(@BindBean GamePlayer gamePlayer, @Bind("uuid") String uuid);

    @SqlQuery("SELECT id FROM players WHERE uuid = :uuid")
    int getID(@Bind("uuid") String uuid);

    @SqlQuery("SELECT points FROM players WHERE id = :id")
    int getPoints(@Bind("id") int id);

    @SqlQuery("SELECT kit FROM players WHERE id = :id")
    KitType getKit(@Bind("id") int id);

}
