package com.eofitg.garbagecan.cmdoperation;

import com.eofitg.garbagecan.CommandGarbageCan;
import com.eofitg.garbagecan.GarbageCan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (!(sender instanceof Player)) { // 检验是否是玩家信息
            GarbageCan.getInstance().getLogger().info(GarbageCan.getMessageFile().get("tips.not-player"));
            return false;
        }
        if (CommandChecker.conform(label, "garbagecan")) {
            CommandGarbageCan.run(((Player) sender).getPlayer());
            return true;
        }
        return false;
    }
}
