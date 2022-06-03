package me.splix.mobarena.arenaHandler.fighters;

import org.bukkit.entity.LivingEntity;

public interface Fighters {

    public default LivingEntity getEntity(){
        return null;
    }
}
