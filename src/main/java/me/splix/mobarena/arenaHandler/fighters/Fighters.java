package me.splix.mobarena.arenaHandler.fighters;

import me.splix.mobarena.playerData.subData.equipmentSet;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface Fighters {


    public default Entity getEntity(){
        return null;
    }

    public default void createMob(Location location, equipmentSet set){
    }

    public default void updateInfo(){
    }

    public default void clearTarget(){

    }
}
