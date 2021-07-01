package me.scholtes.mobrush.kits;

import org.bukkit.entity.Player;

/**
 * A kit that does something at the start of a game
 */
public interface StartKit extends Kit {

    void onStart(Player player);

}
