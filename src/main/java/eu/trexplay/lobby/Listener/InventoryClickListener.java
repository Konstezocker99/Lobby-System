package eu.trexplay.lobby.Listener;

import eu.trexplay.lobby.Lobby;
import org.bukkit.Material;
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
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aFFA")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("FFA"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aBedwars")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("BEDWARS"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§a1vs1")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("1VS1"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aJumpLeage")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("JUMPLEAGE"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aTTT")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("TTT"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aFastBuilder")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("FASTBUILDER"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aMLGRush")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("MLGRUSH"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSpawn")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getHashMapLocations().get("SPAWN"));

            }


        } else if (event.getView().getTitle().equalsIgnoreCase("§aSpecials")) {

            event.setCancelled(true);

        } else if (event.getView().getTitle().equalsIgnoreCase("§aItems")) {

            event.setCancelled(true);

        }

    }

}
