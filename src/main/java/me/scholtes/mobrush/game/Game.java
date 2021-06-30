package me.scholtes.mobrush.game;

import org.bukkit.entity.Player;

import java.util.List;

public interface Game {

    void startGame();

    void endGame();

    void setStatus(GameStatus status);

    GameStatus getStatus();

    List<Player> getPlayers();

}
