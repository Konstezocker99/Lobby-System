package eu.trexplay.lobby.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {

    @EventHandler
    public void CreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

}
