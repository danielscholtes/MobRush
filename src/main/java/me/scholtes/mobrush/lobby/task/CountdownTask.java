package me.scholtes.mobrush.lobby.task;

import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.lobby.Lobby;
import me.scholtes.mobrush.utils.StringUtils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

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
            lobby.broadcast(StringUtils.color("&aGame starting in " + countdown + " seconds!"));
            countdown--;
        }

        for (UUID uuid : lobby.getPlayers()) {
            game.addPlayer(uuid);
        }

        game.setStatus(GameStatus.RUNNING);
        game.startGame();

        lobby.getPlayers().clear();

        lobby.getTaskManager().cancelTask(this.getClass());
    }


}
