package me.splix.mobarena.playerData.subData;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class equipmentSet {

    private ItemStack[] armor = {new ItemStack(Material.LEATHER_HELMET, 1),
            new ItemStack(Material.LEATHER_CHESTPLATE, 1),
            new ItemStack(Material.LEATHER_LEGGINGS, 1),
            new ItemStack(Material.LEATHER_BOOTS, 1)};

    private ItemStack weapon = null;

    public void setHelmet(ItemStack itemStack){
        this.armor[0] = itemStack;
    }

    public ItemStack getHelmet(){
        return this.armor[0];
    }

    public void clearHelmet(){
        armor[0] = new ItemStack(Material.LEATHER_HELMET, 1);
    }

    public void setChestPlate(ItemStack itemStack){
        this.armor[1] = itemStack;
    }

    public ItemStack getChestPlate(){
        return this.armor[1];
    }

    public void clearChestPlate(){
        armor[0] = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    }

    public void setLeggings(ItemStack itemStack){
        this.armor[2] = itemStack;
    }

    public ItemStack getLeggings(){
        return this.armor[2];
    }

    public void clearLeggings(){
        armor[0] = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    }

    public void setBoots(ItemStack itemStack){
        this.armor[3] = itemStack;
    }

    public void clearBoots(){
        armor[0] = new ItemStack(Material.LEATHER_BOOTS, 1);
    }

    public ItemStack getBoots(){
        return this.armor[3];
    }

    public void setWeapon(ItemStack itemStack){
        this.weapon = itemStack;
    }

    public ItemStack getWeapon(){
        return this.weapon;
    }
}
