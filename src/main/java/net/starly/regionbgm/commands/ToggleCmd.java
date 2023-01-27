package net.starly.regionbgm.commands;

import net.starly.regionbgm.data.StringData;
import net.starly.regionbgm.data.ToggleObj;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ToggleCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) return true;
        Player player = (Player) sender;

        StringData message = new StringData();
        if (!player.hasPermission("starly.regionbgm.toggle")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
            return true;
        }

        if (args.length == 0) {
            ToggleObj toggleObj = new ToggleObj(player);
            toggleObj.toggleGUI();
            return true;
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgInvalidCommand()));
        return false;
    }
}
