package eu.trexplay.lobby.Listener;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import eu.trexplay.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(event.getHotbarButton() != -1){
            item = event.getView().getBottomInventory().getItem(event.getHotbarButton());
            if(item == null){
                item = event.getCurrentItem();
            }
        }

        if(item == null || item.getType() == Material.AIR){
            return;
        }
        

        if (event.getView().getTitle().equalsIgnoreCase("§aNavigator")) {

            event.setCancelled(true);

            if (item.getType() == Material.CRAFTING_TABLE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aCityBuild")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("CITYBUILD"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSurvival")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("SURVIVAL"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSkyWars")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("SKYWARS"));

            }
            if (item.getType() == Material.GOLDEN_SWORD && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aFFA")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("FFA"));

            }
            if (item.getType() == Material.RED_BED && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aBedwars")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("BEDWARS"));

            }
            if (item.getType() == Material.DIAMOND_SWORD && item.getItemMeta().getDisplayName().equalsIgnoreCase("§a1vs1")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("1VS1"));

            }
            if (item.getType() == Material.LEATHER_BOOTS && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aJumpLeage")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("JUMPLEAGE"));

            }
            if (item.getType() == Material.LEATHER_CHESTPLATE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aTTT")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("TTT"));

            }
            if (item.getType() == Material.SANDSTONE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aFastBuilder")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("FASTBUILDER"));

            }
            if (item.getType() == Material.STICK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aMLGRush")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("MLGRUSH"));

            }
            if (item.getType() == Material.BEACON && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSpawn")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("SPAWN"));

            }


        } else if (event.getView().getTitle().equalsIgnoreCase("§aSpecials")) {

            event.setCancelled(true);

        } else if (event.getView().getTitle().equalsIgnoreCase("§aItems")) {

            event.setCancelled(true);

        } else if (event.getView().getTitle().equalsIgnoreCase("§aCookie - Clicker")) {

            event.setCancelled(true);

            if (item.getType() == Material.CRAFTING_TABLE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§7Verkaufe deine Cookie's")) {
                if (!(Lobby.getInstance().getHashMapCookies().get(player.getUniqueId()) >= 500)) {
                    player.sendMessage("§cDu musst mindestens 1000 Cookies haben!");
                    return;
                }

                int currentCookies = Lobby.getInstance().getHashMapCookies().get(player.getUniqueId());
                int newCookieAmount = currentCookies - 500;
                Lobby.getInstance().getHashMapCookies().put(player.getUniqueId(), newCookieAmount);
                CoinsAPI.addCoinsDB(player.getUniqueId().toString(), 5);
                player.sendMessage("§7Du hast §a500 §7Cookies für §a5 §7Coins erfolgreich verkauft.");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                player.setScoreboard(Lobby.getInstance().getScoreBoardManager().setScoreBoardtoPlayer(player));

            }
        }

    }

}
