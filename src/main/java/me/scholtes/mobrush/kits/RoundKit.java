package me.scholtes.mobrush.kits;

import org.bukkit.entity.Player;

/**
 * A kit that does something after every round
 */
public interface RoundKit extends Kit {

    void nextRound(Player player);

}
