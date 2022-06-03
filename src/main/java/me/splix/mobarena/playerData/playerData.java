package me.splix.mobarena.playerData;

import me.splix.mobarena.playerData.subData.equipmentSet;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class playerData {

    private Player player;
    private Location oldLocation;
    private pStatus playerStatus = pStatus.FREE;
    private equipmentSet eps;

    public playerData(Player player) {
        this.player = player;
    }

    public equipmentSet getEps() {
        return eps;
    }

    public void setEps(equipmentSet eps) {
        this.eps = eps;
    }

    public Location getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(Location oldLocation) {
        this.oldLocation = oldLocation;
    }

    public pStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(pStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}
