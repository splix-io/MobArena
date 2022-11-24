package me.splix.mobarena.listener;

import me.splix.mobarena.playerData.pStatus;
import me.splix.mobarena.playerData.playerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class mobArenaListener implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onCommand(PlayerCommandPreprocessEvent event){
        if (playerDataHandler.getInstance().getPlayerData(event.getPlayer()).getPlayerStatus() != pStatus.FREE){
            System.out.println(event.getMessage());
            event.setCancelled(true);
            event.getPlayer().sendMessage("You are currently in an arena! Therefore you cannot run any command!");
            event.getPlayer().sendMessage("If you wish to run a command leave the arena");
        }
    }

}
