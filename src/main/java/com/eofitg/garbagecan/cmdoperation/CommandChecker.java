package com.eofitg.garbagecan.cmdoperation;

import com.eofitg.garbagecan.GCConfigReader;
import com.eofitg.garbagecan.GarbageCan;

import java.util.List;

public class CommandChecker {
    public static boolean conform(String requestCommand, String commandName) {
        List<String> cmdList = GCConfigReader.getCmdList(commandName);
        String pluginName = GarbageCan.getPluginName().toLowerCase();
        if (requestCommand.startsWith(pluginName + ":")) {
            requestCommand = requestCommand.substring(pluginName.length() + 1);
        }
        if (cmdList.contains(requestCommand)) {
            return true;
        }
        return false;
    }
}
