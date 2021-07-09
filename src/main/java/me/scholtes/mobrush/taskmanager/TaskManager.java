package me.scholtes.mobrush.taskmanager;

import com.google.common.collect.Sets;
import me.scholtes.mobrush.MobRushPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;


public class TaskManager {

    private final MobRushPlugin plugin;

    private final Set<BukkitRunnable> tasks;

    public TaskManager(MobRushPlugin plugin) {
        this.plugin = plugin;
        this.tasks = new HashSet<>();
    }

    /**
     * Runs a task on the bukkit thread
     */
    public void runTask(BukkitRunnable task) {
        tasks.add(task);
        task.runTask(plugin);
    }

    /**
     * Runs a task repeatedly on the bukkit thread
     *
     * @param delay How long to wait to start the task
     * @param period How often to repeat the task
     */
    public void runTaskTimer(BukkitRunnable task, int delay, int period) {
        tasks.add(task);
        task.runTaskTimer(plugin, delay, period);
    }

    /**
     * Runs a task with a delay
     *
     * @param delay How long to wait to start the task
     */
    public void runTaskLater(BukkitRunnable task, int delay) {
        tasks.add(task);
        task.runTaskLater(plugin, delay);
    }

    /**
     * Cancels the task
     *
     * @param clazz The task to cancel
     */
    public void cancelTask(Class<?> clazz) {
        Sets.newHashSet(tasks).stream()
                .filter(task -> task.getClass() == clazz)
                .forEach(task -> {
                    task.cancel();
                    tasks.remove(task);
                });
    }

}