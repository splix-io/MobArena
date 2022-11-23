package me.splix.mobarena.gui;

import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import me.splix.mobarena.playerData.subData.equipmentSet;
import me.splix.mobarena.utils.GUtils;
import me.splix.mobarena.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class setEquipment implements Listener {
//THIS CLASS IS TO MODIFY THE Fighters equipment.
    // THIS IS STORED IN THE PLAYERDATA CLASS.

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
        GUtils.placeholder(inv, Material.GRAY_STAINED_GLASS_PANE,1, 0, 53);
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
        if (event.getClickedInventory() != inv) {
            return;
        }
        if (event.getCurrentItem() == null || event.isShiftClick())
            return;
        if (event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE)
            return;
        Player player = (Player) event.getWhoClicked();
        playerData playerData = playerDataHandler.getInstance().getPlayerData(player);
        if (event.getSlot() > 53) {
            //Add Actions
            event.setCancelled(true);
            if (Utils.isArmor(event.getCurrentItem())){
                if (Utils.isHelmet(event.getCurrentItem())){
                    playerData.getEps().setHelmet(event.getCurrentItem());

                } else if (Utils.isChestPlate(event.getCurrentItem())) {
                    playerData.getEps().setChestPlate(event.getCurrentItem());

                } else if (Utils.isLeggings(event.getCurrentItem())) {
                    playerData.getEps().setLeggings(event.getCurrentItem());

                } else if (Utils.isBoots(event.getCurrentItem())) {
                    playerData.getEps().setBoots(event.getCurrentItem());

                }
                player.getInventory().remove(event.getCurrentItem());
            }
        }else{
            if (event.getCurrentItem() == playerData.getEps().getHelmet()){
                player.getInventory().addItem(playerData.getEps().getHelmet());
                playerData.getEps().clearHelmet();

            } else if (event.getCurrentItem() == playerData.getEps().getChestPlate()) {
                player.getInventory().addItem(playerData.getEps().getChestPlate());
                playerData.getEps().clearChestPlate();

            } else if (event.getCurrentItem() == playerData.getEps().getLeggings()) {
                player.getInventory().addItem(playerData.getEps().getLeggings());
                playerData.getEps().clearLeggings();

            } else if (event.getCurrentItem() == playerData.getEps().getBoots()) {
                player.getInventory().addItem(playerData.getEps().getBoots());
                playerData.getEps().clearBoots();

            }
        }
    }

}
