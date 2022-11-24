package me.splix.mobarena.arenaHandler;

import me.splix.mobarena.Mobarena;
import me.splix.mobarena.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class arenaCreator {

    private Location pos1;
    private Location pos2;
    private Location playerSpawnLock;
    private String Name;
    private String keyName;
    private int minStart = 3;
    private int maxPlayers = 6;
    private Particle.DustOptions Boarder = new Particle.DustOptions(Color.fromRGB(136, 0, 255), 1F);

    private List<Location> outLineEffect = new ArrayList<>();
    private boolean effectCalCompleted = false;

    public boolean isCompleted(){
        return (getPos1() != null && getPos2() != null &&
                getPlayerSpawnLock() != null && getName() != null && getKeyName() != null);
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
        checkEffect();
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
        checkEffect();
    }

    public void checkEffect(){
        if (getPos1() != null && getPos2() != null){
            new BukkitRunnable(){
                @Override
                public void run(){
                    List<Location> outlinesCalculations = Utils.getOutlineBoarder(getPos1(), getPos2(), 0.75);
                    new BukkitRunnable() {
                        @Override
                        public void run(){
                            outLineEffect.clear();
                            outLineEffect.addAll(outlinesCalculations);
                            effectCalCompleted = true;
                        }
                    }.runTask(Mobarena.getInstance());
                }
            }.runTaskAsynchronously(Mobarena.getInstance());
        }else{
            effectCalCompleted = false;
        }
    }
    public Location getPlayerSpawnLock() {
        return playerSpawnLock;
    }

    public void setPlayerSpawnLock(Location playerSpawnLock) {
        this.playerSpawnLock = playerSpawnLock;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getMinStart() {
        return minStart;
    }

    public void setMinStart(int minStart) {
        this.minStart = minStart;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    private boolean isEffectCalCompleted() {
        return effectCalCompleted;
    }

    public void drawEffect(Player player){
        if (isEffectCalCompleted())
            outLineEffect.forEach(loc -> player.spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0,
                    Boarder));
    }
}
