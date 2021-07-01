package me.scholtes.mobrush.kits.impl;

import me.scholtes.mobrush.itembuilder.ItemBuilder;
import me.scholtes.mobrush.kits.StartKit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class KnightKit implements StartKit {

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.STONE_SWORD)
                                            .build());
    }

}
