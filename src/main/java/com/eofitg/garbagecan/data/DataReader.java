package com.eofitg.garbagecan.data;

import com.eofitg.garbagecan.GCConfigReader;
import com.eofitg.garbagecan.GarbageCan;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader {
    private static final List<String> users = GCConfigReader.getUsers();
    private static final Map< String, Inventory > usercaches = new HashMap<>();
    private static boolean hasBackup = false;
    private static boolean hasDeleted = false;
    public static int getSlot() {
        return -1;
    }
    public static String getMaterial () {
        return  "";
    }
    public static short getDamage () {
        return -1;
    }
    public static String getMeta () {
        return "";
    }
    public static Inventory getCache (String playerName) {
        if(users.contains(playerName)) {
            Inventory re = usercaches.get(playerName);
            return re;
        }
        return null;
    }
    public static void setCache (String playerName, Inventory inv) {
        if(!(users.contains(playerName))) {
            users.add(playerName);
        }
        GCConfigReader.config.set("users",users);
        usercaches.put(playerName, inv);
        GarbageCan.getInstance().saveConfig();
    }
    public static void deleteCache (String playerName) {
        if(users.contains(playerName)) {
            users.remove(playerName);
            GCConfigReader.config.set("users",users);
            usercaches.remove(playerName);
            GarbageCan.getInstance().saveConfig();
        }
    }
    public static boolean getBackup () {
        return hasBackup;
    }
    public static void addBackup () {
        hasBackup = true;
    }
    public static void removeBackup () {
        hasBackup = false;
    }
    public static boolean getDeleteData () {
        return hasDeleted;
    }
    public static void addDeleteData () {
        hasDeleted = true;
    }
    public static void removeDeleteData () {
        hasDeleted = false;
    }
}
