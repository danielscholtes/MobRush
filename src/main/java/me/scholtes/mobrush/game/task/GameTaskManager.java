package me.scholtes.mobrush.game.task;

import com.google.common.collect.Sets;
import me.scholtes.mobrush.MobRushPlugin;
import me.scholtes.mobrush.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;


public class GameTaskManager {

    private final MobRushPlugin plugin;
    private final Game game;

    private final Set<BukkitRunnable> tasks;

    public GameTaskManager(MobRushPlugin plugin, Game game) {
        this.plugin = plugin;
        this.game = game;
        this.tasks = new HashSet<>();
    }

    public void runTask(BukkitRunnable task) {
        tasks.add(task);
        task.runTask(plugin);
    }

    public void runTaskTimer(BukkitRunnable task, int delay, int period) {
        tasks.add(task);
        task.runTaskTimer(plugin, delay, period);
    }

    public void runTaskLater(BukkitRunnable task, int delay) {
        tasks.add(task);
        task.runTaskLater(plugin, delay);
    }

    public void cancelTask(Class<?> clazz) {
        Sets.newHashSet(tasks).stream()
                .filter(task -> task.getClass() == clazz)
                .forEach(task -> {
                    task.cancel();
                    tasks.remove(task);
                });
    }

}
