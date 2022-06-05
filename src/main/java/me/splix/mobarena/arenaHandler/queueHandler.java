package me.splix.mobarena.arenaHandler;

import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.Queue;

public class queueHandler {

    private static Queue<Player> waitingList = new LinkedList<>();

    public static void addPlayer(Player player){
        waitingList.add(player);
        ArenaHandler.getInstance().CheckPlayerAvailability();
    }

    public static Player getNextPlayer(){
        return waitingList.remove();
    }

    public static boolean isPlayerWaiting(){
        return (waitingList.peek() != null);
    }
}
