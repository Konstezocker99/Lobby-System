package eu.trexplay.lobby.Listener.Player;

import eu.trexplay.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (Lobby.getInstance().getConfig().getString("config.joinmessage") == null) {
            event.setJoinMessage(Lobby.getInstance().getConfig().getString("config.joinmessage").replace("%PLAYER%", player.getName()));
        }

        player.teleport(Lobby.getInstance().getHashMapLocations().get("SPAWN"));
        Lobby.getInstance().getInventoryManager().setJoinInventory(player);
        player.setScoreboard(Lobby.getInstance().getScoreBoardManager().setScoreBoardtoPlayer(player));
        for (Player onlineplayer : Bukkit.getOnlinePlayers()) {
            onlineplayer.setScoreboard(Lobby.getInstance().getScoreBoardManager().setScoreBoardtoPlayer(onlineplayer));
        }
    }

}
