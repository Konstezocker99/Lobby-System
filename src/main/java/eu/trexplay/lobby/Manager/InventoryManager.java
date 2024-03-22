package eu.trexplay.lobby.Manager;

import eu.trexplay.lobby.Lobby;
import eu.trexplay.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryManager {
    private Inventory navigatorInventory;
    private Inventory specialInventory;
    private Inventory itemInventory;

    public void setJoinInventory(Player player) {

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setDisplayName("§aNavigator").build());
        player.getInventory().setItem(3, new ItemBuilder(Material.CHEST).setDisplayName("§aSpecials").build());
        player.getInventory().setItem(8, new ItemBuilder(Material.ENDER_CHEST).setDisplayName("§aItems").build());

    }

    public void openNavigatorInventory() {

        navigatorInventory = Bukkit.createInventory(null, 5*9, "§6Navigator");
        navigatorInventory.setItem(1, new ItemBuilder(Material.CRAFTING_TABLE).setDisplayName("§aCityBuild").setLore("§7Rechts-Klick um zum Citybuild zu gelangen").build());
        navigatorInventory.setItem(7, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§aSurvival").setLore("§7Rechts-Klick um zu SMP zu gelangen").build());
        navigatorInventory.setItem(11, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§aSkyWars").setLore("§7Rechts-Klick um zu SkyWars zu gelangen").build());

        refreshNavigatorInventory();
    }

    public void openSpecialInventory() {

        specialInventory = Bukkit.createInventory(null, 3*9, "§aSpecials");


    }

    public void openItemInventory() {

        itemInventory = Bukkit.createInventory(null, 3*9, "§aItems");


    }

    private void refreshNavigatorInventory() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Lobby.getInstance(), new Runnable() {
            @Override
            public void run() {

                //Refresh Navigator INV

            }
        }, 60L, 1L);

    }

}
