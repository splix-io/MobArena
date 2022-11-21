package me.splix.mobarena.arenaHandler.fighters;

import org.bukkit.entity.Entity;

public interface Fighters {

    public default Entity getEntity(){
        return null;
    }

    public default void updateInfo(){
    }

    public default void clearTarget(){

    }
}
