package me.scholtes.mobrush.game.player;

import me.scholtes.mobrush.kits.KitType;

public class GamePlayer {

    private int id;
    private int points;
    private KitType kit;

    public GamePlayer(int id, int points, KitType kit) {
        this.id = id;
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

    public int getId() {
        return this.id;
    }
}
