package me.splix.mobarena.gui;

import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.subData.equipmentSet;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class setEquipment implements Listener {

    private static Inventory inv;
    private static String title;
    private static Component comTitle;
    private static int size;

    public setEquipment(String key, ConfigurationSection section) {
        this.title = "&9&lEquipment GUI";
        this.comTitle = Component.text(GUtils.colour(title));
        this.size = 6 * 9;
        this.inv = Bukkit.createInventory(null, size, comTitle);
        initializeGUI();
    }

    private static void initializeGUI(){
        GUtils.placeholder(inv,Material.GRAY_STAINED_GLASS_PANE,1, 0, 53);
    }

    public static Inventory getInventory(playerData pd){
        Inventory toSend = Bukkit.createInventory(null, size, comTitle);
        toSend.setContents(inv.getContents());
        equipmentSet set = pd.getEps();
        toSend.setItem(11, set.getHelmet());
        toSend.setItem(20, set.getChestPlate());
        toSend.setItem(29, set.getLeggings());
        toSend.setItem(38, set.getBoots());
        toSend.setItem(23, set.getWeapon());

        return toSend;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == inv) {
            //Add Actions
            if (event.getSlot() < 53) {
                event.setCancelled(true);
            }
        }
    }

}
