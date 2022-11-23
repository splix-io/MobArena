package me.splix.mobarena.utils;

import org.bukkit.inventory.ItemStack;

public class Utils {

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
