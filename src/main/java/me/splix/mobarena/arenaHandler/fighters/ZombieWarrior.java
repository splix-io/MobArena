package me.splix.mobarena.arenaHandler.fighters;

import me.splix.mobarena.playerData.subData.equipmentSet;
import net.kyori.adventure.text.Component;
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

    public ZombieWarrior(Player owner) {
        this.owner = owner;
    }

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

    @Override
    public void updateInfo(){
        if (Enemy == null || !Enemy.isDead())
            return;
        double currentMinDistance = 100.0;
        Entity target = null;
        Collection<Entity> targets = self.getWorld().getNearbyEntities(self.getLocation(), 10, 10, 10);
        for (Entity entity: targets){
            if (entity instanceof LivingEntity && !(entity instanceof Player)){
                double currentDistance = self.getLocation().distance(entity.getLocation());
                if (currentDistance < currentMinDistance){
                    target = entity;
                    currentMinDistance = currentDistance;
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
        owner.sendMessage(Component.text("&9Your Warrior is now targeting!"));
    }

    @Override
    public void clearTarget(){
        self.setTarget(null);
        Enemy = null;
    }
}
