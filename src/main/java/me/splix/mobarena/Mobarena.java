package me.splix.mobarena;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mobarena extends JavaPlugin {

    private static NamespacedKey key;

    @Override
    public void onEnable() {
        // Plugin startup logic
        key = new NamespacedKey(this,"MobArena");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static NamespacedKey getKey(){
        return key;
    }
}
