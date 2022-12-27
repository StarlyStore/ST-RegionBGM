package net.starly.store.regionbgm.commands.tabcomplete;

import net.starly.core.data.Config;
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

import static net.starly.store.regionbgm.RegionBGM.plugin;

public class BgmTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();

        if (sender instanceof Player player) {

            if (args.length == 1) {
                StringUtil.copyPartialMatches(args[0], List.of("생성", "편집"), completions);
            } else if (args.length == 2) {

                if(args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[1], List.of("<구역 이름>"), completions);
                } else if (args[0].equalsIgnoreCase("편집")) {

                    Config bgm = new Config("bgm", plugin);
                    ConfigurationSection regions = bgm.getConfig().getConfigurationSection("bgm.");

                    for (String region : regions.getKeys(false)) {
                        StringUtil.copyPartialMatches(args[1], List.of(region), completions);
                    }
                }

            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[2], List.of("<곡>"), completions);
                }

            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[3], List.of("<길이>"), completions);
                }
            } else if (args.length == 5) {
                if (args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[4], List.of("<볼륨>"), completions);
                }

            } else if (args.length == 6) {
                if (args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[5], List.of("<높낮이>"), completions);
                }

            } else if (args.length == 7) {
                if (args[0].equalsIgnoreCase("생성")) {
                    StringUtil.copyPartialMatches(args[6], List.of("<반복 | true/false>"), completions);
                }
            }
        }
        return completions;
    }
}
