package me.scholtes.mobrush.lobby;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.lobby.task.CountdownTask;
import me.scholtes.mobrush.taskmanager.TaskManager;
import me.scholtes.mobrush.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Lobby {

    private final TaskManager taskManager;
    private final Game game;

    private Set<UUID> players;
    private int minPlayers;
    private int countdownTime;

    public Lobby(MobRushPlugin plugin, Game game) {
        this.game = game;

        this.players = new HashSet<>();
        this.minPlayers = 5;
        this.countdownTime = 10;

        this.taskManager = new TaskManager(plugin);
    }

    public void startCountdown() {
        if (game.getStatus() != GameStatus.WAITING) {
            return;
        }

        game.setStatus(GameStatus.STARTING);
        taskManager.runTaskTimer(new CountdownTask(this), 0, countdownTime * 20);
    }

    public void stopCountdown() {
        if (game.getStatus() != GameStatus.STARTING) {
            return;
        }

        broadcast(StringUtils.color("&cGame cancelled! Not enough players to start"));
        game.setStatus(GameStatus.WAITING);
        taskManager.cancelTask(CountdownTask.class);
    }

    public boolean canStartCountdown() {
        return players.size() >= minPlayers;
    }

    public Set<UUID> getPlayers() {
        return this.players;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);

        if (canStartCountdown()) {
            startCountdown();
        }
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);

        if (!canStartCountdown()) {
            stopCountdown();
        }
    }

    public Game getGame() {
        return this.game;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public int getCountdownTime() {
        return this.countdownTime;
    }

    public void broadcast(String message) {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                continue;
            }

            player.sendMessage(message);
        }
    }

}
