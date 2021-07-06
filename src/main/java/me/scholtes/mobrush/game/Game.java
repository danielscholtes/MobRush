package me.scholtes.mobrush.game;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.taskmanager.TaskManager;

import java.util.*;

public abstract class Game {

    private GameStatus status;
    private final Set<UUID> players;

    private final TaskManager taskManager;

    public Game(MobRushPlugin plugin) {
        this.players = new HashSet<>();
        this.status = GameStatus.WAITING;

        this.taskManager = new TaskManager(plugin);
    }

    public abstract void startGame();

    public abstract void endGame();

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public Set<UUID> getPlayers() {
        return this.players;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

}
