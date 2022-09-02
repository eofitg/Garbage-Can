package com.eofitg.garbagecan.button;

import com.eofitg.garbagecan.GarbageCan;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RevokeButton extends ItemStack{
    public static ItemStack ITEM = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    public static String NAME = ChatColor.GREEN + "撤销";
    public static String LORE = ChatColor.GRAY + "撤销刚才的清空操作";
    public ItemStack getItem() {
        return ITEM;
    }
    public RevokeButton() {
        ITEM = Button.buildButton(ITEM, NAME, LORE);
    }
}
