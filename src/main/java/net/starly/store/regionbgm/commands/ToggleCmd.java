package net.starly.store.regionbgm.commands;

import net.starly.core.data.Config;
import net.starly.core.data.MessageConfig;
import net.starly.store.regionbgm.data.ToggleObj;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.starly.store.regionbgm.RegionBGM.plugin;

public class ToggleCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {

            if(args.length == 0) {
                ToggleObj toggleObj = new ToggleObj(player);
                toggleObj.toggleGUI();
                return true;
            }
        }
        return false;
    }
}
