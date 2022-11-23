package me.splix.mobarena.utils;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static final ChatColor[] colors = new ChatColor[] { ChatColor.DARK_AQUA, ChatColor.GOLD, ChatColor.GRAY,
            ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW };
    public static final Random random = new Random();

    public static String chat(String s) {
        if (s == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static Location getRandomLocation(Location loc1, Location loc2) {
        Preconditions.checkArgument(loc1.getWorld() == loc2.getWorld());
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
    }

    public static double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
    }

    public static ChatColor getRandomColor() {
        return colors[random.nextInt(colors.length)];
    }
    public static boolean isArmor(final ItemStack itemStack) {
        return (checkArmorType(itemStack, 0)
                || checkArmorType(itemStack, 1)
                || checkArmorType(itemStack, 2)
                || checkArmorType(itemStack, 3));
    }

    public static boolean isHelmet(final ItemStack itemStack) {
        return checkArmorType(itemStack, 0);
    }

    public static boolean isChestPlate(final ItemStack itemStack) {
        return checkArmorType(itemStack, 1);
    }

    public static boolean isLeggings(final ItemStack itemStack) {
        return checkArmorType(itemStack, 2);
    }

    public static boolean isBoots(final ItemStack itemStack) {
        return checkArmorType(itemStack, 3);
    }

    private static boolean checkArmorType(final ItemStack itemStack, int checkType){
        if (itemStack == null)
            return false;
        final String typeNameString = itemStack.getType().name();
        switch (checkType){
            case 0:
                if (typeNameString.endsWith("_HELMET")){
                    return true;
                }
                break;
            case 1:
                if (typeNameString.endsWith("_CHESTPLATE")){
                    return true;
                }
                break;
            case 2:
                if (typeNameString.endsWith("_LEGGINGS")){
                    return true;
                }
                break;
            case 3:
                if (typeNameString.endsWith("_BOOTS")){
                    return true;
                }
                break;
        }
        return false;
    }
}
