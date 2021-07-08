package me.scholtes.mobrush.game.player;

import me.scholtes.mobrush.kits.KitType;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.util.UUID;


public class GamePlayer {

    // UUID is saved String as Jdbi can't convert a UUID to String
    private String uuid;
    private int points;
    private KitType kit;

    @JdbiConstructor
    public GamePlayer(UUID uuid, int points, KitType kit) {
        this.uuid = uuid.toString();
        this.points = points;
        this.kit = kit;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void removePoints(int points) {
        this.points -= points;
    }

    public void setKit(KitType kit) {
        this.kit = kit;
    }

    public KitType getKit() {
        return this.kit;
    }

    public String getUUID() {
        return this.uuid;
    }
}
