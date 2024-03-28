package eu.trexplay.lobby.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
       
        event.setCancelled(true);
    }

}
