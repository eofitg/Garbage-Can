package com.eofitg.garbagecan.listener;

import com.eofitg.garbagecan.GarbageCan;
import com.eofitg.garbagecan.data.DataReader;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    public static boolean hasUpdate = false;
    private final String invName = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("inventory-name"));
    private final String nullValue = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("errors.NULL-Inventory"));
    private final String unknownError = ChatColor.translateAlternateColorCodes('&', GarbageCan.getMessageFile().get("errors.UNKNOWN-ERROR"));
    @EventHandler
    public void onOpen (InventoryOpenEvent e) { // 容器打开时加载数据
        try {
            Inventory inv = e.getInventory();
            Player player = (Player) e.getPlayer();
            if (inv.getTitle().equals(invName) && !hasUpdate) {
                e.setCancelled(true);
                hasUpdate = true;
                inv = DataReader.getCache(player.getName());
                player.openInventory(inv);
            }
        } catch (Exception ex) {
            e.getPlayer().sendMessage(nullValue);
        }
    }
    @EventHandler
    public void onClose (InventoryCloseEvent e) { // 容器关闭时存储数据
        try {
            Inventory inv = e.getInventory();
            Player player = (Player) e.getPlayer();
            if (inv.getTitle().equals(invName)) {
                if (!DataReader.getDeleteData()) { // 该活动是正常的关闭物品栏，将会覆盖原来的备份数据
                    hasUpdate = false;
                    DataReader.setCache(player.getName(), inv);
                    player.sendMessage("检测到一次关闭容器并成功储存物品栏！");
                } else { // 该活动是执行了一次删除操作后的关闭物品栏，物品栏为空，备份停止更新
                    DataReader.removeDeleteData();
                    player.sendMessage("检测到关闭容器但并未储存物品栏！");
                }
            }
        } catch (Exception ex) {
            e.getPlayer().sendMessage(unknownError);
        }
    }
}
