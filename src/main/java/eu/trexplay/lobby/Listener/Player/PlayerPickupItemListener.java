package eu.trexplay.lobby.Listener.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {event.setCancelled(true);}

}
