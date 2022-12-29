package net.starly.store.regionbgm.commands;

import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import net.starly.store.regionbgm.data.RegionBGMObj;
import net.starly.store.regionbgm.data.StringData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class BGMCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player player) {

            RegionBGMGuiEditorObj bgmEditor = new RegionBGMGuiEditorObj(player);
            RegionBGMObj regionBGMObj = new RegionBGMObj(player);
            StringData message = new StringData();

            if (args.length == 0) {
                message.msgHelp(player);
                return true;
            }

            switch (args[0].toLowerCase()) {

                case "도움말", "help", "guide" -> {
                    message.msgHelp(player);
                    return true;
                }

                case "생성", "create", "만들기" -> {


                    if (args.length == 1) {
                        player.sendMessage("구역 이름을 입력해주세요!");
                    } else if (args.length == 2) {
                        player.sendMessage("브금 이름을 입력해주세요!");
                    } else if (args.length == 3) {
                        player.sendMessage("브금 재생길이를 입력해주세요!");
                    } else if (args.length == 4) {
                        player.sendMessage("브금 불륨를 입력해주세요!");
                    } else if (args.length == 5) {
                        player.sendMessage("브금 피치를 입력해주세요!");
                    } else if (args.length == 6) {
                        player.sendMessage("브금 반복여부를 입력해주세요!");
                    } else {
                        String region = args[1];
                        String bgm = args[2];
                        Integer length = Integer.valueOf(args[3]);
                        Integer volume = Integer.parseInt(args[4]);
                        Integer pitch = Integer.parseInt(args[5]);
                        Boolean loop = Boolean.parseBoolean(args[6]);

                        regionBGMObj.createRegionBGM(region, bgm, length, volume, pitch, loop);
                    }

                    return true;
                }

                case "편집", "편집기", "editor" -> {

                    if (args.length < 2) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgTypingEditRegionName()));
                        return true;
                    }
                    String region = args[1];
                    bgmEditor.openBGMEditor(region);
                    return true;
                }

                case "제거", "삭제", "remove", "delete" -> {

                    if(args.length < 2) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgTypingDelRegionName()));
                        return true;
                    }

                    String region = args[1];
                    regionBGMObj.removeRegionBGM(region);
                    return true;
                }

                case "목록" -> {
                    regionBGMObj.showRegionBGMList();
                    return true;
                }

                default -> {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.errMsgInvalidCommand()));
                    return true;
                }

            }
        }
        return false;
    }
}
