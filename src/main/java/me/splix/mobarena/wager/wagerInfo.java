package me.splix.mobarena.wager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class wagerInfo {

    private Player owner;
    private int money = 0;
    private int crystals = 0;
    private List<ItemStack> items = new ArrayList<>();

    public Player getOwner() {
        return owner;
    }

    public int getMoney() {
        return money;
    }

    public int getCrystals() {
        return crystals;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    // <--

    public void addWager(int amount){
        this.money += amount;
    }

    public void addWager(ItemStack item){
        items.add(item);
    }

    public void addCrystal(int amount){
        this.crystals += amount;
    }

    public void removeWager(int amount){
        this.money -= amount;
    }

    public void removeWager(ItemStack item){
        items.remove(item);
    }

    public void removeCrystal(int amount){
        this.crystals -= amount;
    }
}
