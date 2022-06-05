package me.splix.mobarena.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUtils {

    public static String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colourList(List<String> args){
        List<String> st = new ArrayList<>();
        args.forEach(value -> st.add(colour(value)));
        return st;
    }

    public static ItemStack createItem(Inventory inv, Material materialid, int amount, int invSlot, String displayName, List<String> lore) {
        ItemStack item = null;
        item = new ItemStack(materialid, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colour(displayName));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot, item);
        return item;
    }

    public static ItemStack placeholder(Inventory inv, Material material, int amount, int Slot, int Untill) {
        ItemStack item = null;
        item = new ItemStack(material, amount);
        if (material != Material.AIR) {
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(ChatColor.DARK_GRAY + ""));
            item.setItemMeta(meta);
        }
        for (int i = Slot; i <= Untill; i++)
            inv.setItem(i, item);
        return item;
    }
}
