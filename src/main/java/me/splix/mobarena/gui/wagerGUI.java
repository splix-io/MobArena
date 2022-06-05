package me.splix.mobarena.gui;

import me.splix.mobarena.wager.wagerInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class wagerGUI {


    private static Inventory inv;
    private static String title;
    private static Component comTitle;
    private static int size;

    public wagerGUI(String key, ConfigurationSection section) {
        this.title = "&9&lWager GUI";
        this.comTitle = Component.text(GUtils.colour(title));
        this.size = 3 * 9;
        this.inv = Bukkit.createInventory(null, size, comTitle);
        initializeGUI();
    }

    private static void initializeGUI(){
        GUtils.placeholder(inv, Material.GRAY_STAINED_GLASS_PANE,1, 0, 26);
    }

    public static Inventory getInventory(Player player, wagerInfo info){
        Inventory toSend = Bukkit.createInventory(null, size, comTitle);
        toSend.setContents(inv.getContents());
        ArrayList<String> temp = new ArrayList<>();
        temp.add("You are wagering - " + info.getMoney() + " Money");
        temp.add("&7Click to change amount");
        GUtils.createItem(toSend,Material.SUNFLOWER, 1, 10, "&9&lCurrent Wagered Money", temp);
        temp.clear();



        return toSend;
    }
}
