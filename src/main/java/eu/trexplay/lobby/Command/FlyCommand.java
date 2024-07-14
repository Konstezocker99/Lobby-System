package eu.trexplay.lobby.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor {
    private static List<String> allowedPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (allowedPlayers.contains(player.getName())) {
                player.hasPermission("Lobbby.fly");
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage("§7Du kannst §anun §7fliegen.");
                return true;
            } else {
                player.sendMessage("§7Du kannst nun §cnichtmehr §7fliegen.");
                return false;
            }
        } else {
            commandSender.sendMessage("§cDas kannst du nur als Spieler ausführen.");
            return false;
        }
    }
}
