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

        if(sender instanceof Player player) {

            if(player.hasPermission("starly.regionbgm.toggle")) {
                if (args.length == 0) {
                    ToggleObj toggleObj = new ToggleObj(player);
                    toggleObj.toggleGUI();
                    return true;
                }
            } else {
                StringData message = new StringData();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
            }
        }
        return false;
    }
}
