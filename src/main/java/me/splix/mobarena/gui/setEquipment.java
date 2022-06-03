package me.splix.mobarena.gui;

import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.subData.equipmentSet;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class setEquipment implements Listener {

    private static Inventory inv;
    private static String title;
    private static Component comTitle;
    private static int size;

    public setEquipment(String key, ConfigurationSection section) {
        this.title = "&9&lEquipment GUI";
        this.comTitle = Component.text(colour(title));
        this.size = 6 * 9;
        this.inv = Bukkit.createInventory(null, size, comTitle);
        initializeGUI();
    }

    private static void initializeGUI(){
        placeholder(inv,Material.GRAY_STAINED_GLASS_PANE,1, 0, 53);
    }

    public static Inventory getInventory(playerData pd){
        equipmentSet set = pd.getEps();
        inv.setItem(11, set.getHelmet());
        inv.setItem(20, set.getChestPlate());
        inv.setItem(29, set.getLeggings());
        inv.setItem(38, set.getBoots());
        inv.setItem(23, set.getWeapon());

        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getClickedInventory() == inv){
            //Add Actions
        }
    }

    private static String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static List<String> colourList(List<String> args){
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

    private static ItemStack placeholder(Inventory inv, Material material, int amount, int Slot, int Untill) {
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
