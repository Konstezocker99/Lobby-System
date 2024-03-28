package eu.trexplay.lobby.Command;

import eu.trexplay.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetLocationCommand implements CommandExecutor, TabCompleter {
    public SetLocationCommand() {
        loadmodes();
    }
    private ArrayList<String> modes = new ArrayList<>();
    private void loadmodes() {
        modes.add("CityBuild");
        modes.add("Survival");
        modes.add("SkyWars");
        modes.add("FFA");
        modes.add("Bedwars");
        modes.add("1vs1");
        modes.add("JumpLeage");
        modes.add("TTT");
        modes.add("FastBuilder");
        modes.add("MLGRush");
        modes.add("Spawn");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("")) {
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /setlocation + ");
            sendUsage(player);
            return false;
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "citybuild", "survival", "skywars", "ffa", "bedwars", "1vs1", "jumpleage", "ttt", "fastbuilder", "mlgrush", "spawn" -> Lobby.getInstance().getLocationManager().setLocation(args[0].toUpperCase(), player.getLocation());
                case "help" -> sendHelp(player);
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {return modes;}

    private void sendHelp(Player player) {

        player.sendMessage("");

        player.sendMessage("Es gibt folgende Location zu setzten.");

        for (String modesstring : modes) {
            player.sendMessage("§c- §a " + modesstring);
        }
    }

    private void sendUsage(Player player) {

        player.sendMessage("");

        for (String usagestring : modes) {
            player.sendMessage("§c - §a " + usagestring);
        }
    }
}
