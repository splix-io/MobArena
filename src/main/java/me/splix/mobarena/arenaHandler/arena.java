package me.splix.mobarena.arenaHandler;

import me.splix.mobarena.playerData.pStatus;
import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import me.splix.mobarena.wager.wagerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class arena {

    private arenaState status = arenaState.FREE;
    private Location pos1;
    private Location pos2;
    private Location spawnLocation;
    private int maxPlayers;
    private boolean isAuto;

    private Map<Player, wagerInfo> wagers = new HashMap<>();

    public arena(Location pos1, Location pos2, Location spawnLocation, int maxPlayers, boolean isAuto) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.spawnLocation = spawnLocation;
        this.maxPlayers = maxPlayers;
        this.isAuto = isAuto;
    }

    public void setStatus(arenaState status){
        this.status = status;
    }

    public boolean addPlayer(Player player, wagerInfo info){
        playerData playerData  = playerDataHandler.getInstance().getPlayerData(player);
        if (playerData.getPlayerStatus() != pStatus.FREE){
            return false;
        }
        playerData.setOldLocation(player.getLocation());
        player.teleport(spawnLocation);

        //Send joined message here


        wagers.put(player,info);

        playerData.setPlayerStatus(pStatus.IN_GAME);
        return true;
    }

    public boolean removePlayer(Player player){
        playerData playerData  = playerDataHandler.getInstance().getPlayerData(player);
        playerData.setPlayerStatus(pStatus.FREE);
        player.teleport(playerData.getOldLocation());

        return true;
    }
}
