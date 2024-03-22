package eu.trexplay.lobby.Listener.Player;

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

            switch (item.getItemMeta().getDisplayName()) {

                case "":

                    break;

            }

        } else if (event.getView().getTitle().equalsIgnoreCase("§aSpecials")) {

            event.setCancelled(true);

        } else if (event.getView().getTitle().equalsIgnoreCase("§aItems")) {

            event.setCancelled(true);

        }

    }

}
