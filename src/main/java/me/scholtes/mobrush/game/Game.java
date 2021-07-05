package me.scholtes.mobrush.game;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.taskmanager.TaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Game {

    private GameStatus status;
    private final List<UUID> players;

    private final TaskManager taskManager;

    public Game(MobRushPlugin plugin) {
        this.players = new ArrayList<>();
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

    public List<UUID> getPlayers() {
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
