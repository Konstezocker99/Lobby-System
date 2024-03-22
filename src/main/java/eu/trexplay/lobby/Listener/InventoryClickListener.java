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

        if (event.getView().getTitle().equalsIgnoreCase("§6Navigator")) {

            event.setCancelled(true);

            if (item.getType() == Material.CRAFTING_TABLE && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aCityBuild")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getLocationManager().getLocation("citybuild"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSurvival")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getLocationManager().getLocation("survival"));

            }
            if (item.getType() == Material.GRASS_BLOCK && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSkyWars")) {

                event.setCancelled(true);
                player.teleport(Lobby.getInstance().getLocationManager().getLocation("skywars"));

            }

        } else if (event.getView().getTitle().equalsIgnoreCase("§aSpecials")) {

            event.setCancelled(true);

        } else if (event.getView().getTitle().equalsIgnoreCase("§aItems")) {

            event.setCancelled(true);

        }

    }

}
