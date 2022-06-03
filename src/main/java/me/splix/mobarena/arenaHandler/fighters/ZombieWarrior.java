package me.splix.mobarena.arenaHandler.fighters;

import me.splix.mobarena.playerData.subData.equipmentSet;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ZombieWarrior {

    private Player owner;

    private List<ItemStack> items = new ArrayList<>();
    private Zombie self;

    public void createMob(Location location, equipmentSet set){
        self = (Zombie) owner.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        self.getEquipment().setHelmet(set.getHelmet());
    }


    void updateInfo(){

    }
}
