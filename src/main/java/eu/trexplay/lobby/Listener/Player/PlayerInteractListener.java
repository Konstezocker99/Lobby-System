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

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (action.isLeftClick() && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.HONEY_BLOCK) {

            int oldclick = Lobby.getInstance().getHashMapCookies().get(player.getUniqueId());
            Lobby.getInstance().getHashMapCookies().put(player.getUniqueId(),oldclick + 1);
            player.sendMessage("Honey block clicks: " + Lobby.getInstance().getHashMapCookies().get(player.getUniqueId()));

        } else if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aNavigator") && item.getType() == Material.MUSIC_DISC_CAT) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openNavigatorInventory());

        } else if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSpecials") && item.getType() == Material.CHEST) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openSpecialInventory());

        } else if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aItems") && item.getType() == Material.ENDER_CHEST) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openItemInventory());

        } else if (action.isRightClick() && event.getClickedBlock().getType() == Material.HONEY_BLOCK) {

          //  player.openInventory(Lobby.getInstance().getInventoryManager().openCookieClickerInventory(player));
            player.sendMessage("§cNoch nicht Verfügbar!");

        }
    }
}
