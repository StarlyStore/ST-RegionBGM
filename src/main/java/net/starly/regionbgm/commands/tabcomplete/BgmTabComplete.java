package net.starly.regionbgm.commands.tabcomplete;

import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.regionbgm.RegionBGM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BgmTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();

        if (sender instanceof Player player) {

            if (args.length == 1) {
                StringUtil.copyPartialMatches(args[0], List.of("생성", "편집", "제거", "도움말", "목록"), completions);
            } else if (args.length == 2) {

                if(args[0].equalsIgnoreCase("생성")) {
                    RegionAPI regionAPI = new RegionAPI(RegionBGM.plugin);

                        completions.addAll(regionAPI.getRegionMap().keySet());

                } else if (args[0].equalsIgnoreCase("편집")) {

                    Config bgm = new Config("bgm", RegionBGM.plugin);
                    ConfigurationSection regions = bgm.getConfig().getConfigurationSection("bgm.");
                    completions.addAll(regions.getKeys(false));

                } else if (args[0].equalsIgnoreCase("제거")) {

                    Config bgm = new Config("bgm", RegionBGM.plugin);
                    ConfigurationSection regions = bgm.getConfig().getConfigurationSection("bgm.");
                    completions.addAll(regions.getKeys(false));
                }

            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("생성")) {
                    completions.add("<곡>");
                }

            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("생성")) {
                    completions.add("<길이>");
                }
            } else if (args.length == 5) {
                if (args[0].equalsIgnoreCase("생성")) {
                    completions.add("<볼륨>");
                }

            } else if (args.length == 6) {
                if (args[0].equalsIgnoreCase("생성")) {
                    completions.add("<높낮이>");
                }

            } else if (args.length == 7) {
                if (args[0].equalsIgnoreCase("생성")) {
                    completions.add("<반복 | true/false>");
                }
            }
        }
        return completions;
    }
}
