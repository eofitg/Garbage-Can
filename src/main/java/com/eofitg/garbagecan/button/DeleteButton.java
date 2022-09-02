package com.eofitg.garbagecan.button;

import com.eofitg.garbagecan.GarbageCan;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DeleteButton extends ItemStack{
    public static ItemStack ITEM = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
    public static String NAME = ChatColor.RED + "删除";
    public static String LORE = ChatColor.GRAY + "清空垃圾桶";
    public ItemStack getItem() {
        return ITEM;
    }
    public DeleteButton() {
        ITEM = Button.buildButton(ITEM, NAME, LORE);
    }
}
