package me.scholtes.mobrush.game.task;

import me.scholtes.mobrush.game.mobrushgame.WaveGame;
import org.bukkit.scheduler.BukkitRunnable;


public class WaveTask extends BukkitRunnable {

    private WaveGame game;

    int secondsPassed;
    private int currentDelay;

    public WaveTask(WaveGame game) {
        this.game = game;
        this.currentDelay = 0;
        this.secondsPassed = 0;
    }


    @Override
    public void run() {
        // Checks if the seconds passed from last delay has reached the current delay
        if (secondsPassed >= currentDelay) {
            game.startWave();
            game.setCurrentWave(game.getCurrentWave() + 1);
            updateDelay();
            this.secondsPassed = 0;
        }
    }

    /**
     * Gets the delay till the next wave
     */
    private void updateDelay() {
        // The reason its (current wave - 1) is because the first delay should be the start interval
        int delay = game.getStartInterval() - ((game.getCurrentWave() - 1) * game.getIntervalDecrease());
        this.currentDelay = Math.max(delay, game.getMinInterval());
    }

}
