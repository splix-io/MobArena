package me.splix.mobarena.playerData;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class playerDataHandler {

    private static playerDataHandler instance;
    private Map<Player, playerData> data = new HashMap<>();

    public playerData getPlayerData(Player player){
        return data.get(player);
    }

    public playerData createNew(Player player){
        playerData pd = new playerData(player);
        data.put(player, pd);
        return pd;
    }

    public void removePlayer(Player player){
        data.remove(player);
    }

    public static playerDataHandler getInstance(){
        if (instance == null){
            instance = new playerDataHandler();
        }
        return instance;
    }
}
