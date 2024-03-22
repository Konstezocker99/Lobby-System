package eu.trexplay.lobby;

import eu.trexplay.lobby.Command.SetLocationCommand;
import eu.trexplay.lobby.Listener.Player.PlayerInteractListener;
import eu.trexplay.lobby.Listener.Player.PlayerJoinListener;
import eu.trexplay.lobby.Listener.WeahterChangeListener;
import eu.trexplay.lobby.Manager.*;
import eu.trexplay.lobby.utils.Config;
import eu.trexplay.lobby.utils.Loader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.util.HashMap;

public final class Lobby extends JavaPlugin {

    public static Lobby instance;
    private String PREFIX;
    private Config config;
    private Config mysqlConfig;
    private HikariCPManager hikariCPManager;
    private LocationManager locationManager;
    private InventoryManager inventoryManager;
    private ScoreBoardManager scoreBoardManager;
    private CloudAPIManager cloudAPIManager;
    private final HashMap<String, Location> locations = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;

        config = new Config("config.yml", this.getDataFolder());
        mysqlConfig = new Config("mysql.yml", this.getDataFolder());
        PREFIX = getConfig().getString("config.prefix");

        hikariCPManager = new HikariCPManager();
        locationManager = new LocationManager();
        inventoryManager = new InventoryManager();
        scoreBoardManager = new ScoreBoardManager();
        cloudAPIManager = new CloudAPIManager();

        hikariCPManager.connect();

        registerCommand();
        registerListener();

        Bukkit.getScheduler().scheduleAsyncDelayedTask(getInstance(), new BukkitRunnable() {
            @Override
            public void run() {

                Loader.onEnable();

            }
        }, 2*20);

    }

    @Override
    public void onDisable() {

        hikariCPManager.disconnect();
        instance = null;

    }

    private void registerCommand() {
        getCommand("setlocation").setExecutor(new SetLocationCommand());
    }
    private void registerListener() {
        PluginManager pluginManager = instance.getServer().getPluginManager();
        
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        pluginManager.registerEvents(new WeahterChangeListener(), this);
    }

    public static Lobby getInstance() {
        return instance;
    }

    public HikariCPManager getHikariCPManager() {
        return hikariCPManager;
    }

    public String getPREFIX() {
        return PREFIX;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }

    public CloudAPIManager getCloudAPIManager() {
        return cloudAPIManager;
    }
    public HashMap<String, Location> getHashMapLocations() {return locations;}

    public FileConfiguration getConfig() {
        return this.config.getFileConfiguration();
    }

    public FileConfiguration getMysqlConfig() {
        return this.mysqlConfig.getFileConfiguration();
    }

    public void saveDefaultConfig() {

        config.save();
        mysqlConfig.save();

    }
}
