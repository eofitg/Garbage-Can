package com.eofitg.garbagecan.listener;

import com.eofitg.garbagecan.data.DataReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import sun.misc.GC;

public class PlayerListener implements Listener {
    @EventHandler
    public void  onQuit (PlayerQuitEvent e) { // 玩家离开后销毁数据
        InventoryListener.hasUpdate = false;
        DataReader.deleteCache(e.getPlayer().getName());
    }
}
