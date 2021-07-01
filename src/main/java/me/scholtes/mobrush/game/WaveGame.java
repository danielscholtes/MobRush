package me.scholtes.mobrush.game;

import me.scholtes.mobrush.MobRushPlugin;

public abstract class WaveGame extends Game {

    private int currentWave;
    private final int startInterval;
    private final int intervalDecrease;
    private final int minInterval;

    public WaveGame(MobRushPlugin plugin, int startInterval, int intervalDecrease, int minInterval) {
        super(plugin);

        this.startInterval = startInterval;
        this.intervalDecrease = intervalDecrease;
        this.minInterval = minInterval;
        this.currentWave = 1;
    }

    public abstract void startWave();

    public void setCurrentWave(int wave) {
        this.currentWave = wave;
    }

    public int getCurrentWave() {
        return this.currentWave;
    }

    public int getWaveDelay() {
        // The reason its (current wave - 1) is because the first delay should be the start interval
        int delay = this.startInterval - ((this.currentWave - 1) * this.intervalDecrease);
        return Math.max(delay, this.minInterval);
    }

}
