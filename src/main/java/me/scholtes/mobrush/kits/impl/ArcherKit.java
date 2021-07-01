package me.scholtes.mobrush.kits.impl;

import me.scholtes.mobrush.itembuilder.ItemBuilder;
import me.scholtes.mobrush.kits.RoundKit;
import me.scholtes.mobrush.kits.StartKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArcherKit implements StartKit, RoundKit {

    @Override
    public void nextRound(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.ARROW, 4).build());
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.BOW).build());
    }

}
