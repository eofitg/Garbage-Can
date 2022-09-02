package com.eofitg.garbagecan;

import com.eofitg.garbagecan.cmdoperation.CommandRegister;
import com.eofitg.garbagecan.listener.ClickListener;
import com.eofitg.garbagecan.listener.InventoryListener;
import com.eofitg.garbagecan.listener.PlayerListener;
import com.eofitg.garbagecan.messaging.MessageFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class GarbageCan extends JavaPlugin {

    private static GarbageCan instance;
    private static String pluginName;
    private static MessageFile messageFile;
    public static GarbageCan getInstance() {
        return instance;
    }
    public static String getPluginName() {
        return pluginName;
    }
    public static MessageFile getMessageFile() {
        return messageFile;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        pluginName = instance.getName();
        File mFile = new File("plugins" + File.separator + pluginName, "messages.yml");
        if (!mFile.exists()) {
            this.saveResource("messages.yml", true);
        }
        messageFile = new MessageFile(mFile);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), instance);
        Bukkit.getPluginManager().registerEvents(new ClickListener(), instance);
        CommandRegister.register("garbagecan");
        getLogger().info("GarbageCan插件已经成功加载！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        pluginName = null;
        messageFile = null;
        saveConfig();
        getLogger().info("GarbageCan插件已经成功卸载！");
    }
}
