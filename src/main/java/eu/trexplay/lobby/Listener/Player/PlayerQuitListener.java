package eu.trexplay.lobby.Listener.Player;

import eu.trexplay.lobby.Lobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        if (Lobby.getInstance().getConfig().getString("config.quitmessage") != null) {
            event.setQuitMessage(Lobby.getInstance().getConfig().getString("config.quitmessage").replace("%PLAYER%", event.getPlayer().getName()));
        }


    }

}
