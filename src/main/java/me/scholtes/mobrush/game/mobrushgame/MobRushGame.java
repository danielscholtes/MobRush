package me.scholtes.mobrush.game.mobrushgame;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobRushGame implements Game {

    private MobRushPlugin plugin;

    /*
    TODO: Data for points and kit selection will be retrieved from a data storage class
     (dataStorage.getKit(UUID), dataStorage.getPoints(UUID) etc.)
     */
    private List<UUID> players;
    private GameStatus status;
    private int currentWave;
    public final static int START_INTERVAL = 60;
    public final static int INTERVAL_DECREASE = 5;
    public final static int MIN_INTERVAL = 5;
    private int currentWaveTask = -1;

    /**
     * Whenever a lobby is created an instance of a game will be made
     */
    public MobRushGame(MobRushPlugin plugin) {
        this.plugin = plugin;

        this.players = new ArrayList<>();
        this.status = GameStatus.WAITING;
        this.currentWave = 0;
    }

    /**
     * Starts the game and lets players choose kits, after 10 seconds the first wave starts
     */
    @Override
    public void startGame() {
        setStatus(GameStatus.RUNNING);

        //TODO: Teleport players to world
        //TODO: Add kit selection (10 seconds to choose kit, otherwise defaults to knight kit)
    }

    /**
     * Progresses to the next wave and adds a runnable that will
     * decrease by 5 seconds after every wave. The runnable will begin then next wave
     */
    private void nextWave() {
        //TODO: Mob spawning logic

        // Determines the delay till the next wave
        int delay = START_INTERVAL - (this.currentWave * INTERVAL_DECREASE);
        delay = Math.max(delay, MIN_INTERVAL);
        this.currentWaveTask = new BukkitRunnable() {
            @Override
            public void run() {
                nextWave();
            }
        }.runTaskLater(plugin, delay * 20L).getTaskId();
        this.currentWave++;
    }

    /**
     * Ends the game
     */
    @Override
    public void endGame() {
        //TODO: Send the remaining player(s) back to the lobby

        if (currentWaveTask != -1) {
            Bukkit.getScheduler().cancelTask(currentWaveTask);
        }
        players.clear();
        this.currentWave = 0;
        setStatus(GameStatus.WAITING);
    }

    /**
     * Sets the current wave
     */
    private void setCurrentWave(int wave) {
        this.currentWave = wave;
    }

    /**
     * Gets the current wave
     */
    private int getCurrentWave() {
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
