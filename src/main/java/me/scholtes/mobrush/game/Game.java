package me.scholtes.mobrush.game;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface Game {

    void startGame();

    void endGame();

    void setStatus(GameStatus status);

    GameStatus getStatus();

    List<UUID> getPlayers();

}
