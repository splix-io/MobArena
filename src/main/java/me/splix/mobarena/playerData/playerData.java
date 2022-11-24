package me.splix.mobarena.playerData;

import me.splix.mobarena.arenaHandler.arena;
import me.splix.mobarena.arenaHandler.arenaCreator;
import me.splix.mobarena.arenaHandler.fighters.Fighters;
import me.splix.mobarena.gui.GType;
import me.splix.mobarena.playerData.subData.equipmentSet;
import me.splix.mobarena.wager.wagerInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class playerData {

    private Player player;
    private Location oldLocation;
    private pStatus playerStatus = pStatus.FREE;
    private equipmentSet eps;
    private wagerInfo wager;
    private arenaCreator arenaCreator;
    private Fighters warrior;
    private GType currentInventoryType = GType.NONE;
    private cache placeholderData = new cache();
    private arena currentArena;

    public playerData(Player player) {
        this.player = player;
    }

    public void setWager(wagerInfo wager) {
        this.wager = wager;
    }

    public void setWarrior(Fighters warrior) {
        this.warrior = warrior;
    }

    public Player getPlayer() {
        return player;
    }
    public wagerInfo getWagerInfo() {
        return wager;
    }

    public Fighters getWarrior() {
        return warrior;
    }

    public void createNewEquipmentSet(){
        this.eps = new equipmentSet();
    }

    public arena getCurrentArena() {
        return currentArena;
    }

    public void setCurrentArena(arena currentArena) {
        this.currentArena = currentArena;
    }

    public equipmentSet getEps() {
        return eps;
    }

    public void setEps(equipmentSet eps) {
        this.eps = eps;
    }

    public Location getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(Location oldLocation) {
        this.oldLocation = oldLocation;
    }

    public pStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(pStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public ItemStack getLastItem(){
        return placeholderData.getLastItem();
    }

    public GType getCurrentInventoryType() {
        return currentInventoryType;
    }

    public void setCurrentInventoryType(GType currentInventoryType) {
        this.currentInventoryType = currentInventoryType;
    }

    public void setLastItem(ItemStack item){
        placeholderData.setItem(item);
    }

    public void newArenaCreatorSession(){
        arenaCreator = new arenaCreator();
    }

    public boolean isCreatorEnabled(){
        return (arenaCreator != null);
    }

    public me.splix.mobarena.arenaHandler.arenaCreator getArenaCreator() {
        return arenaCreator;
    }

    public void removeArenaCreator(){
        arenaCreator = null;
    }
}
class cache{
    private ItemStack lastItem;

    public void setItem(ItemStack item){
        lastItem = item;
    }

    public ItemStack getLastItem() {
        return lastItem;
    }
}
