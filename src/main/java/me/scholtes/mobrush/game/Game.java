package me.scholtes.mobrush.game;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.task.WaveTaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Game {

    private GameStatus status;
    private final List<UUID> players;

    public Game() {
        this.players = new ArrayList<>();
        this.status = GameStatus.WAITING;
    }

    public abstract void startGame();

    public abstract void endGame();

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public List<UUID> getPlayers() {
        return this.players;
    }

}
