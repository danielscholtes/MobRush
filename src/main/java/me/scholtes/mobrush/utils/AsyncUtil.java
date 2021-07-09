package me.scholtes.mobrush.utils;

import me.scholtes.mobrush.MobRushPlugin;
import org.bukkit.Bukkit;

public class AsyncUtil {

    private final MobRushPlugin plugin;

    public AsyncUtil(MobRushPlugin plugin) {
        this.plugin = plugin;
    }

    public void runTask(Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

}
