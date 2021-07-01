package me.scholtes.mobrush.game.mobrushgame;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.game.task.GameTaskManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobRushGame implements WaveGame {

    private GameTaskManager taskManager;

    /*
    TODO: Data for points and kit selection will be retrieved from a data storage class
     (dataStorage.getKit(UUID), dataStorage.getPoints(UUID) etc.)
     */
    private List<UUID> players;
    private GameStatus status;
    private int currentWave;

    private int startInterval;
    private int intervalDecrease;
    private int minInterval;

    /**
     * Whenever a lobby is created an instance of a game will be made
     */
    public MobRushGame(GameTaskManager taskManager) {
        this.taskManager = taskManager;

        this.players = new ArrayList<>();
        this.status = GameStatus.WAITING;
        this.currentWave = 1;

        //TODO: Add config, for now values are hardcoded
        this.startInterval = 60;
        this.intervalDecrease = 5;
        this.minInterval = 5;
    }

    /**
     * Starts the game and lets players choose kits, after 10 seconds the first wave starts
     */
    @Override
    public void startGame() {
        setStatus(GameStatus.RUNNING);

        //TODO: Teleport players to world
        //TODO: Add kit selection (10 seconds to choose kit, otherwise defaults to knight kit)
        taskManager.startWaveTask(this);
    }

    /**
     * Spawns in the mobs for the wave
     */
    @Override
    public void startWave() {
        //TODO: Add mob spawning
    }

    @Override
    public int getStartInterval() {
        return this.startInterval;
    }

    @Override
    public int getIntervalDecrease() {
        return this.intervalDecrease;
    }

    @Override
    public int getMinInterval() {
        return this.minInterval;
    }

    /**
     * Ends the game
     */
    @Override
    public void endGame() {
        //TODO: Send the remaining player(s) back to the lobby
        taskManager.cancelTask(this);

        players.clear();
        this.currentWave = 1;
        setStatus(GameStatus.WAITING);
    }

    /**
     * Sets the current wave
     */
    @Override
    public void setCurrentWave(int wave) {
        this.currentWave = wave;
    }

    /**
     * Gets the current wave
     */
    @Override
    public int getCurrentWave() {
        return this.currentWave;
    }

    /**
     * Sets the status of the game
     */
    @Override
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Gets the status of the game
     */
    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    /**
     * Gets the players currently in the game
     */
    @Override
    public List<UUID> getPlayers() {
        return this.players;
    }
}
