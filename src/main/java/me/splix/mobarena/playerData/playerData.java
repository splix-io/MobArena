package me.splix.mobarena.playerData;

import me.splix.mobarena.arenaHandler.fighters.Fighters;
import me.splix.mobarena.playerData.subData.equipmentSet;
import me.splix.mobarena.wager.wagerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class playerData {

    private Player player;
    private Location oldLocation;
    private pStatus playerStatus = pStatus.FREE;
    private equipmentSet eps;
    private wagerInfo wager;
    private Fighters warrior;

    public playerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public wagerInfo getWagerInfo() {
        return wager;
    }

    public Fighters getWarrior() {
        return warrior;
    }

    public void createNewEquipmentSet(){
        this.eps = new equipmentSet();
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
