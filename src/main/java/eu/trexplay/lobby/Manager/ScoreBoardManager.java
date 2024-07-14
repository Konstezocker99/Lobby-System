package eu.trexplay.lobby.Manager;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import eu.trexplay.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardManager {

    public Scoreboard setScoreBoardtoPlayer(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("main", "main", "§9Trexplay");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§8» §aOnline").setScore(5);

        objective.getScore("§7" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("Proxy")).setScore(4);

        objective.getScore("§c ").setScore(3);

        objective.getScore("§8» §aCoins").setScore(2);

        objective.getScore("§7" + CoinsAPI.getCoinsDB(player.getUniqueId().toString())).setScore(1);

        objective.getScore("§c").setScore(0);

        return scoreboard;
    }



}
