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
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    private HashMap<Player, Integer> honeyBlockClicks = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (action.isLeftClick() && event.getClickedBlock().getType() == Material.HONEY_BLOCK) {
            int currentClicks = honeyBlockClicks.getOrDefault(player, 0);
            honeyBlockClicks.put(player, currentClicks + 1);

            player.sendMessage("Honey block clicks: " + honeyBlockClicks.get(player));
        }


        if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aNavigator") && item.getType() == Material.MUSIC_DISC_CAT) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openNavigatorInventory());

        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aSpecials") && item.getType() == Material.CHEST) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openSpecialInventory());

        } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aItems") && item.getType() == Material.ENDER_CHEST) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openItemInventory());

        } else if (action.isRightClick() && event.getClickedBlock().getType() == Material.HONEY_BLOCK) {

            player.openInventory(Lobby.getInstance().getInventoryManager().openCookiClickerInventory();)

        }

    }

}
