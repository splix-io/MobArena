package me.splix.mobarena.arenaHandler.fighters;

import me.splix.mobarena.playerData.subData.equipmentSet;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ZombieWarrior implements Fighters{

    private Player owner;

    private List<ItemStack> items = new ArrayList<>();
    private Zombie self;
    private Entity Enemy;


    public void createMob(Location location, equipmentSet set){
        self = (Zombie) owner.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        PersistentDataContainer data = self.getPersistentDataContainer(); // To use

        self.getEquipment().setHelmet(set.getHelmet());
        self.getEquipment().setChestplate(set.getChestPlate());
        self.getEquipment().setLeggings(set.getLeggings());
        self.getEquipment().setBoots(set.getBoots());
        self.getEquipment().setItemInMainHand(set.getWeapon());
    }

    @Override
    public LivingEntity getEntity() {
        return self;
    }

    void updateInfo(){
        double currentMinDistance = 100.0;
        Entity target = null;
        Collection<Entity> targets = self.getWorld().getNearbyEntities(self.getLocation(), 10, 10, 10);
        for (Entity entity: targets){
            if (entity instanceof LivingEntity && !(entity instanceof Player)){
                double tempDis = self.getLocation().distance(entity.getLocation());
                if (tempDis < currentMinDistance){
                    target = entity;
                    currentMinDistance = tempDis;
                }
            }
        }
        if (target != null){
            Enemy = target;
            setEnemy();
        }
    }

    private void setEnemy(){
        self.setTarget((LivingEntity) Enemy);
    }
}
