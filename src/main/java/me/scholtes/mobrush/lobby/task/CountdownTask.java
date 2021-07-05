package me.scholtes.mobrush.lobby.task;

import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.lobby.Lobby;
import me.scholtes.mobrush.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CountdownTask extends BukkitRunnable {

    private final Lobby lobby;
    private int countdown;

    public CountdownTask(Lobby lobby) {
        this.lobby = lobby;
        this.countdown = lobby.getCountdownTime();
    }

    @Override
    public void run() {
        if (countdown <= 0) {
            for (UUID uuid : lobby.getPlayers()) {
                lobby.getGame().addPlayer(uuid);
            }

            lobby.getGame().setStatus(GameStatus.RUNNING);
            lobby.getGame().startGame();

            lobby.getPlayers().clear();

            lobby.getTaskManager().cancelTask(this.getClass());
            return;
        }

        lobby.broadcast("&aGame starting in " + countdown + " seconds!");
        countdown--;
    }


}
