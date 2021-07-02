package me.scholtes.mobrush.data.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface GamePlayerDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS players (" +
                    "uuid VARCHAR(36) NOT NULL," +
                    "points int NOT NULL," +
                    "kit VARCHAR NOT NULL," +
                    "PRIMARY KEY (uuid)" +
                ");")
    void createTable();

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM players WHERE uuid = :uuid)")
    void exists(@Bind("uuid") String uuid);

    @SqlUpdate("INSERT INTO players (uuid, points, kit) VALUES (:uuid, :points, :kit)")
    void insertPlayer(@Bind("uuid") String uuid, @Bind("points") int points, @Bind("kit") String kit);

    @SqlUpdate("UPDATE players SET points = :points, kit = :kit WHERE uuid = :uuid")
    void updatePlayer(@Bind("uuid") String uuid, @Bind("points") int points, @Bind("kit") String kit);

    @SqlQuery("SELECT points FROM players WHERE uuid = :uuid")
    int getPoints(@Bind("uuid") String uuid);

    @SqlQuery("SELECT kit FROM players WHERE uuid = :uuid")
    String getKit(@Bind("uuid") String uuid);

}
