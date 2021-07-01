package me.scholtes.mobrush.game.task;

import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.WaveGame;
import org.bukkit.Bukkit;

public class WaveTaskManager {

    private final MobRushPlugin plugin;
    private final WaveGame game;

    private int taskID;

    public WaveTaskManager(MobRushPlugin plugin, WaveGame game) {
        this.plugin = plugin;
        this.game = game;
    }

    public void startWaveTask() {
        this.taskID = new WaveTask(this.game).runTaskTimer(plugin, 0L, 20L).getTaskId();
    }

    public void cancelTask() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
