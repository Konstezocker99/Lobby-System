package eu.trexplay.lobby.Manager;

import com.zaxxer.hikari.HikariDataSource;
import eu.trexplay.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HikariCPManager {

    private FileConfiguration mySQLConfig = Lobby.getInstance().getMysqlConfig();
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private final HikariDataSource hikariCP;
    private Connection connection;

    public HikariCPManager() {

        this.hikariCP = new HikariDataSource();
        hikariCP.setIdleTimeout(870000000);
        hikariCP.setMaxLifetime(870000000);
        hikariCP.setConnectionTimeout(870000000);
        hikariCP.setMinimumIdle(1);
        hikariCP.setMaximumPoolSize(3);
        this.host = this.mySQLConfig.getString("mysql.host");
        this.port = this.mySQLConfig.getInt("mysql.port");
        this.database = this.mySQLConfig.getString("mysql.database");
        this.username = this.mySQLConfig.getString("mysql.username");
        this.password = this.mySQLConfig.getString("mysql.password");
        this.hikariCP.setJdbcUrl("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true");
        this.hikariCP.setUsername(this.username);
        this.hikariCP.setPassword(this.password);
        this.hikariCP.addDataSourceProperty("cachePrepStmts", "true");
        this.hikariCP.addDataSourceProperty("prepStmtCacheSize", "250");
        this.hikariCP.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    }
    public void connect() {
        try {
            this.connection = this.hikariCP.getConnection();
            this.createTables();
            Bukkit.getConsoleSender().sendMessage(Lobby.getInstance().getPREFIX() + " §aVerbindung zur §9MySQL-Datenbank §awurde hergestellt§8.");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Lobby.getInstance().getPREFIX() + " §cVerbindung zur §9MySQL-Datenbank §ckonnte nicht hergestellt werden§8.");
            e.printStackTrace();

        }
    }

    public void disconnect() {
        try {
            if (this.connection != null) {
                this.hikariCP.close();
                Bukkit.getConsoleSender().sendMessage(Lobby.getInstance().getPREFIX() + "§cVerbindung zur §9MySQL-Datenbank §cwurde getrennt§8.");
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Lobby.getInstance().getPREFIX() + "§cVerbindung der §9MySQL-Datenbank §ckonnte getrennt werden§8!");
            e.printStackTrace();
        }

    }

    private void createTables() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS Location (name VARCHAR(50), world VARCHAR(50), x VARCHAR(50), y VARCHAR(50), z VARCHAR(50), yaw VARCHAR(50), pitch VARCHAR(50))";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(Lobby.getInstance().getPREFIX() + "§cBeim erstellen der §9Datenbank-Tabellen §cist ein Fehler aufgetreten§8!");
            e.printStackTrace();
        }

    }

    public HikariDataSource getHikariCP() {
        return hikariCP;
    }

    public Connection getConnection() {
        return this.connection;
    }

}
