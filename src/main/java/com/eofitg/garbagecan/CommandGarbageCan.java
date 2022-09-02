package com.eofitg.garbagecan;

import com.eofitg.garbagecan.button.DeleteButton;
import com.eofitg.garbagecan.button.PlaceHolder;
import com.eofitg.garbagecan.button.RevokeButton;
import org.bukkit.Bukkit;;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandGarbageCan {
    public static void run (Player player) {
        String invName = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("inventory-name"));
        Inventory inv = Bukkit.createInventory(player, 5 * 9, invName);
        ItemStack placeHolder = new PlaceHolder().getItem();
        ItemStack revoke = new RevokeButton().getItem();
        ItemStack delete = new DeleteButton().getItem();
        inv.setItem(36, revoke);
        inv.setItem(44, delete);
        for (int i = 37; i < 44; i ++) {
            inv.setItem(i, placeHolder);
        }
        player.openInventory(inv);
    }

}
