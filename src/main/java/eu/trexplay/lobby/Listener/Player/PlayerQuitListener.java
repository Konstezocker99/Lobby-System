package eu.trexplay.lobby.Listener.Player;

import eu.trexplay.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        if (Lobby.getInstance().getConfig().getString("config.quitmessage") != null) {
            event.setQuitMessage(Lobby.getInstance().getConfig().getString("config.quitmessage").replace("%PLAYER%", event.getPlayer().getName()));
        }

        setCookieFromHashMapTODataBase(event.getPlayer());




    }

    private void setCookieFromHashMapTODataBase(Player player) {
        try {

            PreparedStatement checkStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("SELECT * FROM Cookie WHERE UUID = ?");
            checkStatement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {

                PreparedStatement updateStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("UPDATE Cookie SET number = ? WHERE UUID = ?");
                updateStatement.setString(1, String.valueOf(Lobby.getInstance().getHashMapCookies().get(player.getUniqueId())));
                updateStatement.setString(2, player.getUniqueId().toString());

                updateStatement.executeUpdate();
                updateStatement.close();

            } else {

                PreparedStatement insertStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("INSERT INTO Cookie (UUID, number) VALUES (?, ?)");
                insertStatement.setString(1, player.getUniqueId().toString());
                insertStatement.setString(2, String.valueOf(Lobby.getInstance().getHashMapCookies().get(player.getUniqueId())));
                insertStatement.executeUpdate();
                insertStatement.close();

            }

            checkStatement.close();
            resultSet.close();

        }catch (Exception exception) {
            Lobby.getInstance().getLogger().info("Es ist ein Fehler aufgetretten!");
            exception.printStackTrace();
        }
    }

}
