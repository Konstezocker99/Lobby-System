package eu.trexplay.lobby.Manager;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.service.ICloudService;
import eu.trexplay.lobby.Lobby;
import eu.trexplay.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class InventoryManager {
    private Inventory navigatorInventory;
    private Inventory specialInventory;
    private Inventory itemInventory;
    private Inventory cookieClickerInventory;

    public void setJoinInventory(Player player) {

        player.getInventory().clear();
        player.getInventory().setItem(1, new ItemBuilder(Material.MUSIC_DISC_CAT).setDisplayName("§aNavigator").build());
        player.getInventory().setItem(5, new ItemBuilder(Material.CHEST).setDisplayName("§aSpecials").build());
        player.getInventory().setItem(7, new ItemBuilder(Material.ENDER_CHEST).setDisplayName("§aItems").build());
    }

    public Inventory openNavigatorInventory() {

        navigatorInventory = Bukkit.createInventory(null, 6*9, "§aNavigator");
        navigatorInventory.setItem(4, new ItemBuilder(Material.BEACON).setDisplayName("§aSpawn").setLore("§7Rechts-Klick um zum §aSpawn §7zu gelangen").build());
        navigatorInventory.setItem(11, new ItemBuilder(Material.CRAFTING_TABLE).setDisplayName("§aCityBuild").setLore("§7Rechts-Klick um zum §aCitybuild §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());
        navigatorInventory.setItem(13, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§aSurvival").setLore("§7Rechts-Klick um zu §aSMP §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());
        navigatorInventory.setItem(15, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§aSkyWars").setLore("§7Rechts-Klick um zu §aSkyWars §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());
        navigatorInventory.setItem(19, new ItemBuilder(Material.GOLDEN_SWORD).setDisplayName("§aFFA").setLore("§7Rechts-Klick um zu §aFFA §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        navigatorInventory.setItem(21, new ItemBuilder(Material.RED_BED).setDisplayName("§aBedwars").setLore("§7Rechts-Klick um zu §aBedWars §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());
        navigatorInventory.setItem(23, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§a1vs1").setLore("§7Rechts-Klick um zu §a1vs1 §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        navigatorInventory.setItem(25, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aJumpLeage").setLore("§7Rechts-Klick um zu §aJumpLeague §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        navigatorInventory.setItem(29, new ItemBuilder(Material.SANDSTONE).setDisplayName("§aFastBuilder").setLore("§7Rechts-Klick um zu §aFastBuilder §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());
        navigatorInventory.setItem(31, new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("§aTTT").setLore("§7Rechts-Klick um zu §aTTT §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        navigatorInventory.setItem(33, new ItemBuilder(Material.STICK).setDisplayName("§aMLGRush").setLore("§7Rechts-Klick um zu §aMLGRush §7zu gelangen", "§6Status: §4Offline", "§6Spieler anzahl: §6 -/-").build());

        refreshNavigatorInventory();
        return navigatorInventory;
    }

    public Inventory openSpecialInventory() {

        specialInventory = Bukkit.createInventory(null, 3*9, "§aSpecials");
        specialInventory.setItem(11, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aSchuhe").setLore("§7Rechts-Klick um alle §aSchuhe §7anzusehen").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        specialInventory.setItem(15, new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§aKöpfe").setLore("§7Rechts-Klick um alle §aTeam/Youtuber-Köpfe §7anzusehen").build());

        return specialInventory;
    }

    public Inventory openItemInventory() {

        itemInventory = Bukkit.createInventory(null, 3*9, "§aItems");

        return itemInventory;
    }

    public Inventory openCookieClickerInventory(Player player) {

        cookieClickerInventory = Bukkit.createInventory(null, 3*9, "§aCookie - Clicker");
        cookieClickerInventory.setItem(11, new ItemBuilder(Material.COOKIE).setDisplayName("§2TopListe").setLore("§aNOCH NICHT VERFÜGBAR!").build());
        cookieClickerInventory.setItem(13, new ItemBuilder(Material.PLAYER_HEAD).setHead(player.getName()).setDisplayName("§6Meine Cookies§7: §c" + Lobby.getInstance().getHashMapCookies().get(player.getUniqueId())).build());
        cookieClickerInventory.setItem(15, new ItemBuilder(Material.CRAFTING_TABLE).setDisplayName("§3Verkaufe deine Cookie's").setLore("§61000 §bCookies §7= §610 §bCoins").build());

        return cookieClickerInventory;
    }

    public void refreshNavigatorInventory() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Lobby.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (CloudAPI.getInstance().getCloudServiceGroupManager().getAllCachedObjects() != null) {

                    for(ICloudService service : CloudAPI.getInstance().getCloudServiceManager().getAllCachedObjects()) {

                        if (service.isOnline()) {

                            switch (service.getName()) {

                                case "Citybuild-1":
                                    navigatorInventory.getItem(11).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("CityBuild")));
                                    break;

                                case "Survival-1":
                                    navigatorInventory.getItem(13).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("Survival")));
                                    break;

                                case "SkyWars-1":
                                    navigatorInventory.getItem(15).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("SkyWars")));
                                    break;

                                case "FFA-1":
                                    navigatorInventory.getItem(19).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("FFA")));
                                    break;

                                case "BedWars-1":
                                    navigatorInventory.getItem(21).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("BedWars")));
                                    break;

                                case "1vs1-1":
                                    navigatorInventory.getItem(23).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("1vs1")));
                                    break;

                                case "JumpLeage-1":
                                    navigatorInventory.getItem(25).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("JumpLeage")));
                                    break;

                                case "FastBuilder-1":
                                    navigatorInventory.getItem(29).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("FastBuilder")));
                                    break;

                                case "TTT-1":
                                    navigatorInventory.getItem(31).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("TTT")));
                                    break;

                                case "MLGRush-1":
                                    navigatorInventory.getItem(33).setLore(Arrays.asList(
                                            "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                                            "§7Status: §aOnline",
                                            "§7Spieler anzahl: §a" + Lobby.getInstance().getCloudAPIManager().getOnlinePlayerCount("MLGRush")));
                                    break;

                            }
                        }
                    }
                }
            }
        }, 60L, 1L);

    }

    private List itemLore(ICloudService service) {
        List<String> lore = Arrays.asList(
                "§7Rechts-Klick um zum §a" + service.getName() + " §7zu gelangen",
                "§7Status: §aOnline",
                "§7Spieler anzahl: §a" + service.getOnlinePlayers());
        return lore;
    }

}
