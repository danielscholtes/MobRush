package me.scholtes.mobrush.game.mobrushgame;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.GameStatus;
import me.scholtes.mobrush.game.WaveGame;
import me.scholtes.mobrush.game.task.WaveTask;

public class MobRushGame extends WaveGame {

    /**
     * Whenever a lobby is created an instance of a game will be made
     */
    public MobRushGame(MobRushPlugin plugin) {
        //TODO: Add config, for now values are hardcoded
        super(plugin, 60, 5, 5);
    }

    /**
     * Starts the game and lets players choose kits, after 10 seconds the first wave starts
     */
    @Override
    public void startGame() {
        setStatus(GameStatus.RUNNING);

        //TODO: Teleport players to world
        //TODO: Add kit selection (10 seconds to choose kit, otherwise defaults to knight kit)
        getTaskManager().runTaskTimer(new WaveTask(this), 0, 20);
    }

    /**
     * Spawns in the mobs for the wave
     */
    @Override
    public void startWave() {
        //TODO: Add mob spawning
    }

    /**
     * Ends the game
     */
    @Override
    public void endGame() {
        //TODO: Send the remaining player(s) back to the lobby
        getTaskManager().cancelTask(WaveTask.class);

        getPlayers().clear();
        setCurrentWave(1);
        setStatus(GameStatus.WAITING);
    }

}
