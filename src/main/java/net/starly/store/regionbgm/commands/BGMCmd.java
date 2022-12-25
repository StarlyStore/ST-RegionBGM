package net.starly.store.regionbgm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BGMCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {
            if(args.length == 0) {
                return true;
            }

            switch (args[0]) {

                case "생성", "create", "만들기" -> {
                    String region = args[1];
                    String bgm = args[2];
                    Integer length = Integer.parseInt(args[3]);
                    Integer volume = Integer.parseInt(args[4]);
                    Integer pitch = Integer.parseInt(args[5]);
                    Boolean loop = Boolean.parseBoolean(args[6]);

                    return true;

                }
            }
        }
        return false;
    }
}
