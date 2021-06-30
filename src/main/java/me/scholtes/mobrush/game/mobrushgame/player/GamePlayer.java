package me.scholtes.mobrush.game.mobrushgame.player;

public class GamePlayer {

    private int points;
    //TODO: Implement Kit choice. Will do once Kit PR has been merged

    public GamePlayer(int points) {
        this.points = points;
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
}
