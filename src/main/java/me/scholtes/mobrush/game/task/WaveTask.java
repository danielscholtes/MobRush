package me.scholtes.mobrush.game.task;

import me.scholtes.mobrush.game.WaveGame;
import org.bukkit.scheduler.BukkitRunnable;


public class WaveTask extends BukkitRunnable {

    private final WaveGame game;

    int secondsPassed;

    public WaveTask(WaveGame game) {
        this.game = game;
        this.secondsPassed = 0;
    }

    @Override
    public void run() {
        // Checks if the seconds passed from last delay has reached the current delay
        if (secondsPassed >= game.getWaveDelay()) {
            game.startWave();
            game.setCurrentWave(game.getCurrentWave() + 1);
            this.secondsPassed = 0;
        }
    }

}
