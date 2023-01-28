package net.starly.regionbgm.commands.tabcomplete;

import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.regionbgm.RegionBGMMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BgmTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList("생성", "편집", "제거", "도움말", "목록"), completions);
        } else if (args.length == 2) {
            if (args[0].equals("생성")) {
                completions.addAll(new RegionAPI(RegionBGMMain.getPlugin()).getRegionMap().keySet());
            } else if (args[0].equals("편집")) {
                ConfigurationSection regions = new Config("bgm", RegionBGMMain.getPlugin()).getConfigurationSection("bgm");
                if (regions != null) completions.addAll(regions.getKeys(false));
            } else if (args[0].equals("제거")) {
                ConfigurationSection regions = new Config("bgm", RegionBGMMain.getPlugin()).getConfigurationSection("bgm");
                if (regions != null) completions.addAll(regions.getKeys(false));
            }
        } else if (args.length == 3) {
            if (args[0].equals("생성")) completions.add("<곡>");
        } else if (args.length == 4) {
            if (args[0].equals("생성")) completions.add("<길이>");
        } else if (args.length == 5) {
            if (args[0].equals("생성")) completions.add("<볼륨>");
        } else if (args.length == 6) {
            if (args[0].equals("생성")) completions.add("<높낮이>");
        } else if (args.length == 7) {
            if (args[0].equals("생성")) completions.add("<반복 | true/false>");
        }

        return completions;
    }
}
