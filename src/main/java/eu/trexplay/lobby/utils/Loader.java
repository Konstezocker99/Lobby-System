package eu.trexplay.lobby.utils;

import eu.trexplay.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Loader {

    private static void setLocationToHashMap() {
        try {

            Lobby.getInstance().getHashMapLocations().clear();

            PreparedStatement preparedStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("SELECT * FROM Location");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");

                String worldName = resultSet.getString("world");

                double x = Double.parseDouble(resultSet.getString("x"));
                double y = Double.parseDouble(resultSet.getString("y"));
                double z = Double.parseDouble(resultSet.getString("z"));

                float yaw = Float.parseFloat(resultSet.getString("yaw"));
                float pitch = Float.parseFloat(resultSet.getString("pitch"));

                if(Bukkit.getServer().getWorld(worldName) != null){

                    World world = Bukkit.getServer().getWorld(worldName);

                    Location location = new Location(world, x, y, z, yaw, pitch);
                    Lobby.getInstance().getHashMapLocations().put(name, location);

                } else {

                    Bukkit.getConsoleSender().sendMessage("NULL!");

                }
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

    }

    private static void setCookieToHashMap(){
        System.out.println("DEBUG");
        try {
            System.out.println("DEBUG");

            PreparedStatement preparedStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("SELECT * FROM Cookie");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String user = resultSet.getString("UUID");

                Integer number = (resultSet.getInt("number"));

                Lobby.getInstance().getHashMapCookies().put(UUID.fromString(user), number);
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        }

    }

    public static void onEnable() {

        Bukkit.getConsoleSender().sendMessage("____________________________________________________");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("             TrexPlay - Lobby - System");
        Bukkit.getConsoleSender().sendMessage("                   Version: 1.0.0 ");
        Bukkit.getConsoleSender().sendMessage("                       By Sabi, EinNick.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("____________________________________________________");

        setLocationToHashMap();
        setCookieToHashMap();
    }

    public static void onDisable() {

        Bukkit.getConsoleSender().sendMessage("____________________________________________________");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("             TrexPlay - Lobby - System");
        Bukkit.getConsoleSender().sendMessage("                   Version: 1.0.0 ");
        Bukkit.getConsoleSender().sendMessage("                       By Sabi, EinNiki.");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("____________________________________________________");

    }

}
