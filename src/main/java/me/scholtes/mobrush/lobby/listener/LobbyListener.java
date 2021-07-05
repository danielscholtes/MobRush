package me.scholtes.mobrush.lobby.listener;

import me.scholtes.mobrush.lobby.Lobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyListener implements Listener {

    private Lobby lobby;

    public LobbyListener(Lobby lobby) {
        this.lobby = lobby;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        lobby.addPlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        lobby.removePlayer(event.getPlayer().getUniqueId());
    }

}
