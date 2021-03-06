package me.splix.mobarena.arenaHandler;

import me.splix.mobarena.arenaHandler.fighters.Fighters;
import me.splix.mobarena.playerData.pStatus;
import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import me.splix.mobarena.wager.wagerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class arena implements Listener {

    private arenaState status = arenaState.FREE;
    private Location pos1;
    private Location pos2;
    private Location spawnLocation;
    private int maxPlayers;
    private int minStartCount = 3;
    private boolean isAuto;

    private int TotalAlive = 0;
    private ArrayList<Player> EliminatedPlayers = new ArrayList<>();
    private Map<Player, wagerInfo> wagers = new HashMap<>();
    private Map<Fighters, Player> warriors = new HashMap<>();

    public arena(Location pos1, Location pos2, Location spawnLocation, int maxPlayers, boolean isAuto) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.spawnLocation = spawnLocation;
        this.maxPlayers = maxPlayers;
        this.isAuto = isAuto;
    }

    public void checkStartRequirements(){
        if (TotalAlive >= minStartCount){
            status = arenaState.IN_PROGRESS;
            //Add starting script.

            //UpdateCall
            ArenaHandler.getInstance().arenaStateChange(this);
        }
    }

    public void setStatus(arenaState status){
        this.status = status;
    }

    public arenaState getStatus() {
        return status;
    }

    public boolean isSpaceAvailable(){
        return (TotalAlive != maxPlayers);
    }

    public boolean addPlayer(Player player, wagerInfo info, Fighters fighter){
        playerData playerData  = playerDataHandler.getInstance().getPlayerData(player);
        if (playerData.getPlayerStatus() != pStatus.FREE){
            return false;
        }
        playerData.setOldLocation(player.getLocation());
        player.teleport(spawnLocation);

        //Send joined message here


        wagers.put(player,info);
        warriors.put(fighter, player);

        playerData.setPlayerStatus(pStatus.IN_GAME);
        TotalAlive += 1;
        return true;
    }

    public boolean removePlayer(Player player){
        playerData playerData  = playerDataHandler.getInstance().getPlayerData(player);
        playerData.setPlayerStatus(pStatus.FREE);
        player.teleport(playerData.getOldLocation());

        return true;
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event){
        if (warriors.keySet().contains(event.getEntity())){
            if (!warriors.keySet().contains(event.getTarget()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if (warriors.keySet().contains(event.getEntity())){
            event.getDrops().clear();
            EliminatedPlayers.add(warriors.get(event.getEntity()));
            warriors.get(event.getEntity()).sendMessage("Your Warrior has fallen! You have been eliminated you placed " + TotalAlive);
            TotalAlive -= 1;
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if (warriors.keySet().contains(event.getDamager())){
            warriors.get(event.getDamager()).sendMessage("Your Warrior has attacked (" + warriors.get(event.getEntity()).getName() + ") Warrior!");

        }
    }
}
