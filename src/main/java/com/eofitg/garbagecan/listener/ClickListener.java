package com.eofitg.garbagecan.listener;

import com.eofitg.garbagecan.GCConfigReader;
import com.eofitg.garbagecan.GarbageCan;
import com.eofitg.garbagecan.button.DeleteButton;
import com.eofitg.garbagecan.button.PlaceHolder;
import com.eofitg.garbagecan.button.RevokeButton;
import com.eofitg.garbagecan.data.DataFilter;
import com.eofitg.garbagecan.data.DataReader;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class ClickListener implements Listener {
    private final String invName = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("inventory-name"));
    @EventHandler
    public void onClick (InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        String playerName = player.getName();
        ItemStack clickItem = e.getCurrentItem();
        Inventory in = e.getInventory();
        if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()) {
            return;
        }
        if (clickItem == null) {
            return;
        }
        if (in.getTitle().equals(invName)) {
            if (clickItem.equals(new RevokeButton().getItem())) { // 撤销键
                e.setCancelled(true);
                player.closeInventory();
                if (!GCConfigReader.getUsers().contains(playerName) || !DataReader.getBackup()) { // 没有上一步
                    String sendMessage = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("tips.without-backup"));
                    player.sendMessage(sendMessage);
                    return;
                }
                Inventory backup = DataReader.getCache(playerName);
                player.openInventory(backup);
                DataReader.deleteCache(playerName);
                DataReader.removeBackup();
                DataReader.removeDeleteData();
            } else if (clickItem.equals(new DeleteButton().getItem())) { // 删除键
                e.setCancelled(true);
                DataReader.addBackup();
                DataReader.addDeleteData();
                for(int i=0;i<36;i++)in.clear(i);
                player.closeInventory();
                // sendLog(inv, player);
                String sendMessage = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("tips.success-delete"));
                player.sendMessage(sendMessage);
            } else if (clickItem.equals(new PlaceHolder().getItem())) { // 占位物品
                e.setCancelled(true);
            }
        }
    }
    private void sendLog (InventoryView inv, Player player) {
        for (int i = 0; i < 36; i ++) {
            ItemStack is = inv.getItem(i);
            if (is != null && !is.getData().toString().equals("AIR(-1)")) { // 1.12不支持null，只好用AIR检测
                String iss = is.toString();
                String itemData = is.getData().toString(); // MaterialData
                player.sendMessage(itemData);
                String itemType = is.getData().getItemType().toString(); // Material
                String iT = DataFilter.getMaterial(itemData); // My Material
                short damage = -1;
                if (itemData.contains("(")) {
                    damage = DataFilter.getDamage(itemData); // My Material damage
                }
                int itemAmount = is.getAmount();
                String itemD = Material.getMaterial(itemType).toString(); // Material
                String itemMeta = is.getItemMeta().toString(); // Meta
                String em = is.getEnchantments().toString(); // Enchantment
                player.sendMessage("Slot " + i + ": {");
                player.sendMessage("  ItemStack: " + iss);
                player.sendMessage("  MaterialData and amount: " + itemData + ", " + itemAmount);
                player.sendMessage("  Material: " + itemType + "  " + itemD);
                player.sendMessage("  My Material: " + iT);
                if (damage >= 0) {
                    player.sendMessage("  My damage: " + " (" + damage + ") ");
                }
                player.sendMessage("  Meta: " + itemMeta);
                player.sendMessage("  Enchantments: " + em);
                player.sendMessage("} ");
            }
        }
    }
}
