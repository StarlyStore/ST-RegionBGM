package net.starly.regionbgm.commands;

import net.starly.regionbgm.data.RegionBGMGuiEditorObj;
import net.starly.regionbgm.data.StringData;
import net.starly.regionbgm.data.RegionBGMObj;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class BGMCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        StringData message = new StringData();

        RegionBGMGuiEditorObj bgmEditor = new RegionBGMGuiEditorObj(player);
        RegionBGMObj regionBGMObj = new RegionBGMObj(player);

        if (args.length == 0) {
            message.msgHelp(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "도움말":
            case "help":
            case "?": {
                if (!player.hasPermission("starly.regionbgm.help")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
                    return true;
                }

                message.msgHelp(player);
                return true;
            }

            case "생성":
            case "create": {
                if (!player.hasPermission("starly.regionbgm.create")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
                    return true;
                }

                if (args.length == 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingRegionName()));
                    return true;
                } else if (args.length == 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingBGMName()));
                    return true;
                } else if (args.length == 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingLength()));
                    return true;
                } else if (args.length == 4) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingVolume()));
                    return true;
                } else if (args.length == 5) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingPitch()));
                    return true;
                } else if (args.length == 6) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgCreateTypingLoop()));
                    return true;
                }

                String region = args[1];
                String bgm = args[2];
                Integer length = Integer.valueOf(args[3]);
                Integer volume = Integer.parseInt(args[4]);
                Integer pitch = Integer.parseInt(args[5]);
                Boolean loop = Boolean.parseBoolean(args[6]);

                regionBGMObj.createRegionBGM(region, bgm, length, volume, pitch, loop);
                return true;
            }

            case "편집":
            case "edit": {
                if (!player.hasPermission("starly.regionbgm.edit")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgTypingEditRegionName()));
                    return true;
                }

                String region = args[1];
                bgmEditor.openBGMEditor(region);
                return true;
            }

            case "제거":
            case "remove": {
                if (!player.hasPermission("starly.regionbgm.remove")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
                    return true;
                }

                if (args.length < 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgTypingDelRegionName()));
                    return true;
                }

                String region = args[1];
                regionBGMObj.removeRegionBGM(region);
                return true;
            }

            case "목록":
            case "list": {
                if (!player.hasPermission("starly.regionbgm.list")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgNoPermission()));
                    return true;
                }

                regionBGMObj.showRegionBGMList();
                return true;
            }

            default: {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgInvalidCommand()));
                return true;
            }
        }
    }
}
