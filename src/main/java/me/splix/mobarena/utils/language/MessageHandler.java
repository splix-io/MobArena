package me.splix.mobarena.utils.language;

import me.splix.mobarena.Mobarena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler {

    private static Mobarena instance;
    private static Map<String, Component> storedMessages = new HashMap<>();
    static FileConfiguration Data;
    private static final File DataFile = new File(Mobarena.getInstance().getDataFolder(), "language.yml");

    private static void CreateFolder(){
        if (!DataFile.exists()) {
            DataFile.getParentFile().mkdirs();
            Mobarena.getInstance().saveResource("language.yml", false);
        }
    }

    public MessageHandler() throws IOException, InvalidConfigurationException {
        instance = Mobarena.getInstance();
        CreateFolder();
        Data = new YamlConfiguration();
        Data.load(DataFile);
        Data.getKeys(false).forEach(key -> registerMessage(key, Data.getString("English." + key)));
    }

    public static void registerMessage(String key, String value){
        storedMessages.put(key, createText(value));
    }

    public static boolean overrideMessage(String key, String value){
        if (!storedMessages.containsKey(key))
            return false;
        storedMessages.replace(key, createText(value));
        return true;
    }
    private static Component createText(String text){
        return MiniMessage.miniMessage().deserialize(text);
    }

    public static void sendPlayerMessage(Player player, String... keys){
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = keys).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            player.sendMessage(storedMessages.get(s));
            b++;
        }
    }
}
