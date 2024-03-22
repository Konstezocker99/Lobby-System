package eu.trexplay.lobby.Manager;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import org.bukkit.entity.Player;

public class CloudAPIManager {

    public String getOnlinePlayerHighestRang(Player player) {

        return String.valueOf(PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId()));
    }

    public Integer getOnlinePlayerCount(String group) {

        if(CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(group) != null){

            return CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(group).getOnlinePlayerCount();

        }

        return null;
    }

}
