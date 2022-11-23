package me.splix.mobarena.gui;

import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import me.splix.mobarena.utils.GUtils;
import me.splix.mobarena.utils.language.MessageHandler;
import me.splix.mobarena.wager.wagerInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class wagerGUI implements Listener {


    private static Inventory invMain;
    private static Inventory invItems;
    private static String title;
    private static String titleItem;
    private static Component comTitle;
    private static Component comTitleItem;
    private static int size;
    private static int sizeItem;

    public wagerGUI(String key, ConfigurationSection section) {
        title = "&9&lWager GUI";
        comTitle = Component.text(GUtils.colour(title));
        size = 3 * 9;
        invMain = Bukkit.createInventory(null, size, comTitle);
        titleItem = "&9&lItems Wagered";
        comTitleItem = Component.text(GUtils.colour(titleItem));
        sizeItem = 4 * 9;
        invItems = Bukkit.createInventory(null, sizeItem, comTitleItem);
        initializeGUI();
    }

    private static void initializeGUI(){
        GUtils.placeholder(invMain, Material.GRAY_STAINED_GLASS_PANE,1, 0, 26);
        GUtils.placeholder(invItems, Material.GRAY_STAINED_GLASS_PANE,1, 0, 35);
    }

    public static Inventory getInventoryMainShow(Player player, wagerInfo info){
        playerDataHandler.getInstance().getPlayerData(player).setCurrentInventoryType(GType.WAGER_GUI);
        Inventory toSend = Bukkit.createInventory(null, size, comTitle);
        toSend.setContents(invMain.getContents());
        GUtils.createItem(toSend, Material.SUNFLOWER, 1, 10, "&9&lCurrent Wagered Money",
                "You are wagering - " + info.getMoney() + " Money", "&7Click to change amount");

        GUtils.createItem(toSend, Material.END_CRYSTAL, 1, 12, "&9&lCurrent Wagered Crystals",
                "You are wagering - " + info.getCrystals() + " Crystals", "&7Click to change amount");

        GUtils.createItem(toSend, Material.CHEST, 1, 14, "&9&lCurrent Wagered Items",
                "&7&lClick to see all wagered Items");

        return toSend;
    }

    public static Inventory openItemWageredGUI(Player player, wagerInfo info){
        return openItemWageredGUI(playerDataHandler.getInstance().getPlayerData(player), info);
    }

    public static Inventory openItemWageredGUI(playerData player, wagerInfo info){
        player.setCurrentInventoryType(GType.WAGER_ITEM);
        Inventory toSend = Bukkit.createInventory(null, sizeItem, comTitleItem);
        toSend.setContents(invItems.getContents());
        int index = 10;
        for (ItemStack itemStack: info.getItems()){
            GUtils.createItem(toSend, itemStack.getType(), 1, index, String.valueOf(itemStack.displayName()), "&7Shift click to &cunwager &7this Item");
            index++;
            if (((index + 1) % 9) == 0)
                index += 2;
        }
        return toSend;
    }

    @EventHandler
    public void ClickWagerGUI(InventoryClickEvent event){
        if (event.getClickedInventory() != invMain){
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot()){
            case 10:
                //Open Money GUI.
            case 12:
                //Open Crystal GUI
            case 14:
                //Open Items GUI
                player.openInventory(openItemWageredGUI(player,
                        playerDataHandler.getInstance().getPlayerData(player).getWagerInfo()));

        }
    }

    @EventHandler
    public void ClickItemGUI(InventoryClickEvent event){
        if (event.getClickedInventory() != invItems){
            return;
        }
        event.setCancelled(true);
        if (event.isShiftClick()){
            return;
        }
        if (event.getCurrentItem() == null){
            return;
        }
        playerData pd = playerDataHandler.getInstance().getPlayerData((Player) event.getWhoClicked());
        if (event.getSlot() < sizeItem){
            //Clicked their own Items
            pd.setLastItem(event.getCurrentItem()); // Updating data before sending message so message will be correct
            // and not null
            MessageHandler.sendPlayerMessage((Player) event.getWhoClicked(),"ADDED_ITEM_WAGER");
            //
            pd.getWagerInfo().addWager(event.getCurrentItem());
            pd.getPlayer().getInventory().remove(event.getCurrentItem());
            //pd.getPlayer().getInventory().addItem(pd.getWagerInfo().removeWager(event.getCurrentItem()));
        }else {
            if (pd.getWagerInfo().getItems().contains(event.getCurrentItem())) {
                pd.getPlayer().getInventory().addItem(pd.getWagerInfo().removeWager(event.getCurrentItem()));
                MessageHandler.sendPlayerMessage((Player) event.getWhoClicked(),"REMOVED_ITEM_WAGER");
            }
        }

    }
}

//TODO:
// Crystal GUI
// Money GUI
