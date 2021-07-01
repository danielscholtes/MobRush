package me.scholtes.mobrush.kits.impl;

import me.scholtes.mobrush.itembuilder.ItemBuilder;
import me.scholtes.mobrush.kits.RoundKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MageKit implements RoundKit {

    @Override
    public void nextRound(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.ENDER_PEARL)
                                            .build());
    }
}
