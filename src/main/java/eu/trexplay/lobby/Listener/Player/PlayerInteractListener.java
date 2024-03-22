package eu.trexplay.lobby.Listener.Player;

import eu.trexplay.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aNavigator") && item.getType() == Material.COMPASS) {

            Lobby.getInstance().getInventoryManager().openNavigatorInventory();

        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSpecials") && item.getType() == Material.CHEST) {

            Lobby.getInstance().getInventoryManager().openSpecialInventory();

        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aItems") && item.getType() == Material.ENDER_CHEST) {

            Lobby.getInstance().getInventoryManager().openItemInventory();

        }

    }

}
