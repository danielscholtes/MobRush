package me.scholtes.mobrush.lobby.task;

import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask extends BukkitRunnable {

    private final Lobby lobby;
    private final Game game;
    private int countdown;

    public CountdownTask(Lobby lobby) {
        this.lobby = lobby;
        this.game = lobby.getGame();
        this.countdown = lobby.getCountdownTime();
    }

    @Override
    public void run() {
        if (countdown > 0) {
            lobby.broadcast(ChatColor.GREEN + "Game starting in " + countdown + " seconds!");
            countdown--;
        }

        lobby.getPlayers().forEach(game::addPlayer);
        lobby.getPlayers().clear();

        game.setStatus(GameStatus.RUNNING);
        game.startGame();

        lobby.getTaskManager().cancelTask(this.getClass());
    }


}
