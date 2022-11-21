package me.splix.mobarena;

import com.leonardobishop.quests.bukkit.BukkitQuestsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mobarena extends JavaPlugin {

    private static NamespacedKey key;
    private static BukkitQuestsPlugin questsPlugin;
    private static Mobarena instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance = this;
        key = new NamespacedKey(this,"MobArena");
        questsPlugin = (BukkitQuestsPlugin) Bukkit.getPluginManager().getPlugin("Quests");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static NamespacedKey getKey(){
        return key;
    }

    public static Mobarena getInstance(){
        return instance;
    }
}
