package com.eofitg.garbagecan;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class GCConfigReader {
    public static FileConfiguration config = GarbageCan.getInstance().getConfig();
    private static final List<String> users = config.getStringList("users");
    public static List<String> getCmdList (String commandName) {
        return config.getStringList("commands." + commandName);
    }

    public static List<String> getUsers () {
        return users;
    }

}
