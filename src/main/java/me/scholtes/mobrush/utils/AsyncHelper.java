package me.scholtes.mobrush.utils;

import me.scholtes.mobrush.MobRushPlugin;
import org.bukkit.Bukkit;

public class AsyncHelper {

    private final MobRushPlugin plugin;

    public AsyncHelper(MobRushPlugin plugin) {
        this.plugin = plugin;
    }

    public void runTask(Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

}
