package com.eofitg.garbagecan.button;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlaceHolder extends ItemStack{
    public static ItemStack ITEM = new ItemStack(Material.BARRIER, 1);
    public static String NAME = "  ";
    public static String LORE = null;
    public ItemStack getItem() {
        return ITEM;
    }
    public PlaceHolder() {
        ITEM = Button.buildButton(ITEM, NAME, LORE);
    }
}
