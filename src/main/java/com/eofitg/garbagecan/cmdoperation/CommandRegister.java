package com.eofitg.garbagecan.cmdoperation;

import com.eofitg.garbagecan.GCConfigReader;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;

public class CommandRegister {
    public static void register (String commandName) {
        List<String> cmdList = GCConfigReader.getCmdList(commandName);
        for (String s : cmdList) {
            Objects.requireNonNull(Bukkit.getPluginCommand(s)).setExecutor(new CommandHandler());
        }
    }
}
