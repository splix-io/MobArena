package me.splix.mobarena.utils;

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

    public static ItemStack createItem(Inventory inv, Material materialid, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item = null;
        item = new ItemStack(materialid, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colour(displayName));
        ArrayList<Component> lores = new ArrayList<>();
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = loreString).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            lores.add(Component.text(colour(s)));
            b++;
        }
        meta.lore(lores);
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
    public static void fillBoarder(Inventory inv, ItemStack item){
        if (inv.getSize() == 9){
            setRow(inv, 0, item);
            return; // Done
        }
        setColumn(inv, 0, item); // Doing the sides first because this prevents overlap of the corners
        setColumn(inv, 8, item);

        setRange(inv, 1, 8, 1, item);
        setRange(inv, inv.getSize()-8, inv.getSize()-1, 1, item);
    }


    public static void setColumn(Inventory inv, int startingPoint, ItemStack stack){
        setRange(inv, startingPoint, inv.getSize(), 9, stack);
    }

    public static void setRow(Inventory inv, int row, ItemStack stack){
        int startPos = row * 9;
        setRange(inv, startPos, startPos + 9, 1, stack);
    }


    private static void setRange(Inventory inv, int startPos, int endPos, int jumpAmount, ItemStack stack){
        for(int i = startPos; i < endPos; i += jumpAmount)
            inv.setItem(i, stack);
    }
}
