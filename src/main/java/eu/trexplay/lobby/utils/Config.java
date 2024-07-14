package eu.trexplay.lobby.utils;

import eu.trexplay.lobby.Lobby;
import eu.trexplay.lobby.Manager.HikariCPManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private FileConfiguration fileConfiguration;
    private File file;

    public Config(String name, File path) {
        this.file = new File(path, name);
        if (!this.file.exists()) {
            path.mkdirs();

            try {
                this.file.createNewFile();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        this.fileConfiguration = new YamlConfiguration();

        try {
            this.fileConfiguration.load(this.file);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        if (this.fileConfiguration.getKeys(true).isEmpty() && name.equals("config.yml")) {
            this.fileConfiguration.set("config.prefix", "Lobby");
            this.fileConfiguration.set("config.joinmessage", "Der Spieler &a%PLAYER% ist gejoint!");
            this.fileConfiguration.set("config.quitmessage", "Der Spieler &a%PLAYER% ist gequitet!");
            this.save();
        }

        if (this.fileConfiguration.getKeys(true).isEmpty() && name.equals("mysql.yml")) {
            this.fileConfiguration.set("mysql.host", "127.0.0.1");
            this.fileConfiguration.set("mysql.port", 3306);
            this.fileConfiguration.set("mysql.database", "databasename");
            this.fileConfiguration.set("mysql.username", "root");
            this.fileConfiguration.set("mysql.password", "You Password");
            this.save();
        }

    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getFileConfiguration() {
        return this.fileConfiguration;
    }

    public void reload() {
        try {
            this.fileConfiguration.load(this.file);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void save() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
