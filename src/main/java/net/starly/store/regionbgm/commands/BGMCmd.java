package net.starly.store.regionbgm.commands;

import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import net.starly.store.regionbgm.data.RegionBGMObj;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BGMCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {


        if(sender instanceof Player player) {

            RegionBGMGuiEditorObj bgmEditor = new RegionBGMGuiEditorObj(player);
            RegionBGMObj regionBGMObj = new RegionBGMObj(player);

            if(args.length == 0) {
                return true;
            }

            switch (args[0].toLowerCase()) {

                case "생성", "create", "만들기" -> {
                    String region = args[1];
                    String bgm = args[2];
                    Integer length = Integer.parseInt(args[3]);
                    Integer volume = Integer.parseInt(args[4]);
                    Integer pitch = Integer.parseInt(args[5]);
                    Boolean loop = Boolean.parseBoolean(args[6]);
                    regionBGMObj.createRegionBGM(region, bgm, length, volume, pitch, loop);

                    return true;

                }

                case "편집", "편집기", "editor" -> {
                    String region = args[1];
                    bgmEditor.openBGMEditor(region);
                    return true;
                }
            }
        }
        return false;
    }
}
