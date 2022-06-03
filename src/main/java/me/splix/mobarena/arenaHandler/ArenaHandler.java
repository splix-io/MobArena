package me.splix.mobarena.arenaHandler;


import me.splix.mobarena.Mobarena;
import me.splix.mobarena.playerData.playerData;
import me.splix.mobarena.playerData.playerDataHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public class ArenaHandler {

    private static ArenaHandler instance;
    private ArrayList<arena> allArenas = new ArrayList<>();
    private ArrayList<arena> deactivatedArenas = new ArrayList<>();

    public void registerNewArena(arena ar){
        allArenas.add(ar);
        if (deactivatedArenas.contains(ar))
            deactivatedArenas.remove(ar);
    }

    public void disableArena(arena ar){
        deactivatedArenas.add(ar);
        allArenas.remove(ar);
    }

    public ArrayList<arena> getAllArenas() {
        return allArenas;
    }

    public arena getNextFreeArena(){
        for (arena arena: allArenas){
            if (arena.getStatus() == arenaState.FREE){
                return arena;
            }
        }
        return null;
    }

    public boolean isArenaFree(){
        if (getNextFreeArena() != null)
            return true;
        return false;
    }

    public void arenaStateChange(arena arena){
        if (arena.getStatus() == arenaState.FREE){
            while (queueHandler.isPlayerWaiting()){
                if (arena.isSpaceAvailable()){
                    playerData playerData = playerDataHandler.getInstance().getPlayerData(queueHandler.getNextPlayer());
                    arena.addPlayer(playerData.getPlayer(),playerData.getWagerInfo(), playerData.getWarrior());
                }else{
                    break;
                }
            }

        } else if (arena.getStatus() == arenaState.IN_PROGRESS) {
            JavaPlugin.getPlugin(Mobarena.class).getLogger().log(Level.ALL, "Arena Starting!");
        }
    }



    public static ArenaHandler getInstance(){
        if (instance == null){
            instance = new ArenaHandler();
        }
        return instance;
    }

}
