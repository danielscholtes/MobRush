package me.scholtes.mobrush.game.mobrushgame;

import me.scholtes.mobrush.game.Game;

public interface WaveGame extends Game {

    int getCurrentWave();

    void setCurrentWave(int wave);

    void startWave();

    int getStartInterval();

    int getIntervalDecrease();

    int getMinInterval();

}
