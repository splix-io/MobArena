package me.splix.mobarena.arenaHandler;

import me.splix.mobarena.Mobarena;
import me.splix.mobarena.arenaHandler.fighters.Fighters;
import me.splix.mobarena.playerData.pStatus;
import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import me.splix.mobarena.utils.Utils;
import me.splix.mobarena.wager.wagerInfo;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.ItemStack;
import ru.xezard.glow.data.glow.Glow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class arena implements Listener {

    private Mobarena pluginInstance;
    private arenaState status = arenaState.FREE;
    private String key;
    private String Name;
    private Location pos1;
    private Location pos2;
    private Location spawnLocation;
    private int maxPlayers;
    private int minStartCount;
    private boolean isAuto;

    private int TotalAlive = 0;
    private ArrayList<Player> EliminatedPlayers = new ArrayList<>();
    private Map<Player, wagerInfo> wagers = new HashMap<>();
    private Map<Fighters, Player> warriors = new HashMap<>();
    private List<LivingEntity> allTargetable = new ArrayList<>();
    private List<Glow> Effects = new ArrayList<>();
    public arena(String keyIden, String name, Location pos1, Location pos2, Location spawnLocation, int minPlayers, int maxPlayers, boolean isAuto) {
        this.pluginInstance = Mobarena.getInstance();
        this.key = keyIden;
        this.Name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.spawnLocation = spawnLocation;
        this.minStartCount = minPlayers;
        this.maxPlayers = maxPlayers;
        this.isAuto = isAuto;
    }

    public void checkStartRequirements(){
        if (TotalAlive >= minStartCount){
            status = arenaState.IN_PROGRESS;
            //Add starting script.
            startLoadUp();
            //UpdateCall
            ArenaHandler.getInstance().arenaStateChange(this);
        }
    }

    public void startLoadUp(){
        //Spawn in the fighters.
        for (Player player: wagers.keySet()){
            playerData data = playerDataHandler.getInstance().getPlayerData(player);
            warriors.put(data.getWarrior(), player);
            data.getWarrior().createMob(Utils.getRandomLocation(pos1, pos2),data.getEps());
            Glow glowEffect = Glow.builder()
                            .color(Utils.getRandomColor())
                                    .name("PL:" + player.getName())
                                            .build();
            glowEffect.addHolders(data.getWarrior().getEntity());
            glowEffect.display(player);
            Effects.add(glowEffect);
            allTargetable.add((LivingEntity) data.getWarrior().getEntity());
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
        playerData.setCurrentArena(this);
        playerData.setOldLocation(player.getLocation());
        player.teleport(spawnLocation);

        //Send joined message here


        wagers.put(player, info);

        playerData.setPlayerStatus(pStatus.IN_GAME);
        TotalAlive += 1;
        checkStartRequirements();
        return true;
    }

    public boolean removePlayer(Player player){
        playerData playerData  = playerDataHandler.getInstance().getPlayerData(player);
        playerData.setPlayerStatus(pStatus.FREE);
        playerData.setCurrentArena(null);
        player.teleport(playerData.getOldLocation());
        if (!EliminatedPlayers.contains(player)){
            //Return Wagers

        }

        return true;
    }


    public void checkWinStatus(){
        if (TotalAlive == 1){
            status = arenaState.CLEARING;
            clearArena();
        }
    }

    public void clearArena(){
        // Implement winner award
        // Implement vars clear
        // arenaHandler links.

        //Award Cal
        int money = 0;
        int crystals = 0;
        ArrayList<ItemStack> items = new ArrayList<>();
        for (wagerInfo wager: wagers.values()){
            money += wager.getMoney();
            crystals += wager.getCrystals();
            items.addAll(wager.getItems());
        }

        // Clear Glow Effects for all
        Effects.forEach(Glow::destroy);
        for (Player player: wagers.keySet()){
            playerData playerData = playerDataHandler.getInstance().getPlayerData(player);
            if (!EliminatedPlayers.contains(player)){
                //Winner!

            }
            if (playerData.getCurrentArena() == this) {
                removePlayer(player);
            }
        }
    }

    void arenaHandlerHeartbeat(){ // Run sync loop
        for (Fighters fighters: warriors.keySet()){
            Entity entity = fighters.getEntity();

            fighters.updateInfo();

            if (entity instanceof Mob mob){
                LivingEntity targeted = mob.getTarget();

                if (canTarget(targeted)){
                    continue;
                }
                fighters.clearTarget();
                List<Entity> possible = mob.getNearbyEntities(10,10,10);
                for (Entity targetPossible: possible){
                    if (targetPossible instanceof LivingEntity livingEntityTarget) {
                        if (allTargetable.contains(livingEntityTarget)) {
                            mob.setTarget(livingEntityTarget);
                            break;
                        }
                    }
                }
            }

        }
    }

    private boolean canTarget(LivingEntity target){
        if (allTargetable.contains(target))
            return true;
        return false;
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event){
        if (allTargetable.contains(event.getEntity())){
            if (!allTargetable.contains(event.getTarget()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if (allTargetable.contains(event.getEntity())){
            event.getDrops().clear();
            EliminatedPlayers.add(warriors.get(event.getEntity()));
            warriors.get(event.getEntity()).sendMessage("Your Warrior has fallen! You have been eliminated you placed " + TotalAlive);
            TotalAlive -= 1;
            checkWinStatus();
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof LivingEntity livingEntity && event.getEntity() instanceof LivingEntity damagedLivingEntity) {
            if (allTargetable.contains(livingEntity)) {
                warriors.get(livingEntity).sendMessage("Your Warrior has attacked (" + warriors.get(damagedLivingEntity).getName() + ") Warrior!");
            }
        }else{
            pluginInstance.getLogger().log(Level.INFO, "Entity Damaged by: " + event.getDamager());
        }
    }
}
