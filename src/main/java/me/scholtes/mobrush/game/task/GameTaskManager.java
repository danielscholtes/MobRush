package me.scholtes.mobrush.game.task;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.Game;
import me.scholtes.mobrush.game.mobrushgame.WaveGame;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class GameTaskManager {

    private MobRushPlugin plugin;
    private Map<Game, Integer> gameTaskID;

    public GameTaskManager(MobRushPlugin plugin) {
        this.plugin = plugin;
        gameTaskID = new HashMap<>();
    }

    public void startWaveTask(WaveGame game) {
        int taskID = new WaveTask(game).runTaskTimer(plugin, 0L, 20L).getTaskId();
        gameTaskID.put(game, taskID);
    }

    public void cancelTask(Game game) {
        Bukkit.getScheduler().cancelTask(gameTaskID.get(game));
        gameTaskID.remove(game);
    }

}
