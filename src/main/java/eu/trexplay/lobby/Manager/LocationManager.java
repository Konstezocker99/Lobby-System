package eu.trexplay.lobby.Manager;

import eu.trexplay.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LocationManager {

    public void setLocation(String locationname, Location location) {

        try {

            PreparedStatement checkStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("SELECT * FROM Location WHERE name = ?");
            checkStatement.setString(1, locationname);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {

                PreparedStatement updateStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("UPDATE Location SET world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ? WHERE name = ?");
                updateStatement.setString(1, location.getWorld().getName());
                updateStatement.setString(2, String.valueOf(location.getX()));
                updateStatement.setString(3, String.valueOf(location.getY()));
                updateStatement.setString(4, String.valueOf(location.getZ()));
                updateStatement.setString(5, String.valueOf(location.getYaw()));
                updateStatement.setString(6, String.valueOf(location.getPitch()));
                updateStatement.setString(7, locationname);
                updateStatement.executeUpdate();
                updateStatement.close();

            } else {

                PreparedStatement insertStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("INSERT INTO Location (name, world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?, ?)");
                insertStatement.setString(1, locationname);
                insertStatement.setString(2, location.getWorld().getName());
                insertStatement.setString(3, String.valueOf(location.getX()));
                insertStatement.setString(4, String.valueOf(location.getY()));
                insertStatement.setString(5, String.valueOf(location.getZ()));
                insertStatement.setString(6, String.valueOf(location.getYaw()));
                insertStatement.setString(7, String.valueOf(location.getPitch()));
                insertStatement.executeUpdate();
                insertStatement.close();

            }

            checkStatement.close();
            resultSet.close();

        }catch (Exception exception) {
            Lobby.getInstance().getLogger().info("&vEs ist ein Fehler aufgetretten!");
            exception.printStackTrace();
        }

    }

    public Location getLocation(String locationname) {

        try {
            PreparedStatement preparedStatement = Lobby.getInstance().getHikariCPManager().getConnection().prepareStatement("SELECT * FROM Location WHERE name = ?");
            preparedStatement.setString(1, locationname.toUpperCase());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String worldName = resultSet.getString("world");
                double x = Double.parseDouble(resultSet.getString("x"));
                double y = Double.parseDouble(resultSet.getString("y"));
                double z = Double.parseDouble(resultSet.getString("z"));
                float yaw = Float.parseFloat(resultSet.getString("yaw"));
                float pitch = Float.parseFloat(resultSet.getString("pitch"));

                return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
            }

            preparedStatement.close();
        } catch (Exception exception) {
            Lobby.getInstance().getLogger().info("&cEs ist ein Fehler aufgetreten!");
            exception.printStackTrace();
        }


        return null;
    }

}
