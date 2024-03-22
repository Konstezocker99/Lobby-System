package eu.trexplay.lobby.Listener.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsumeListener implements Listener {

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {event.setCancelled(true);}

}
