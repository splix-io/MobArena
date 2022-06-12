package me.splix.mobarena.utils.language;

import me.splix.mobarena.Mobarena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MessageHandler {

    private static Mobarena instance;
    private static Map<String, Component> storedMessages = new HashMap<>();

    public MessageHandler(Mobarena instance) {
        this.instance = instance;
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
