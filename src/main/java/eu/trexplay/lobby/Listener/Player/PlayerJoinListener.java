package eu.trexplay.lobby.Listener.Player;

import eu.trexplay.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage("");

      //  player.teleport(Lobby.getInstance().getLocationManager().getLocation("spawn"));
        player.teleport(Lobby.getInstance().getHashMapLocations().get("SPAWN"));
        Lobby.getInstance().getInventoryManager().setJoinInventory(player);
       // player.setScoreboard(Lobby.getInstance().getScoreBoardManager().setScoreBoardtoPlayer(player));
    }

}
