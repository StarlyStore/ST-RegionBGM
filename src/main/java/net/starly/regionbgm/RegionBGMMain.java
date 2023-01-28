package net.starly.regionbgm;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.regionbgm.commands.BGMCmd;
import net.starly.regionbgm.commands.ToggleCmd;
import net.starly.regionbgm.commands.tabcomplete.BgmTab;
import net.starly.regionbgm.data.RegionMapData;
import net.starly.regionbgm.event.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class RegionBGMMain extends JavaPlugin {
    private static RegionBGMMain plugin;
    private static final Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        // DEPENDENCY
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            log.warning("[" + plugin.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttp://starly.kr/discord");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else if (Bukkit.getPluginManager().getPlugin("ST-Region") == null) {
            log.warning("[" + plugin.getName() + "] ST-Region 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttp://starly.kr/discord");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        plugin = this;
        new Metrics(this, 17241);

        // CONFIG
        Config bgm = new Config("bgm", this);

        // EVENT
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionEnterListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("bgm").setExecutor(new ToggleCmd());
        Bukkit.getPluginCommand("regionbgm").setExecutor(new BGMCmd());
        Bukkit.getPluginCommand("regionbgm").setTabCompleter(new BgmTab());

        // PLAY_SOUND
        if (bgm.getConfig().getConfigurationSection("bgm") != null) {
            bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    RegionAPI regionAPI = new RegionAPI(RegionBGMMain.getPlugin());

                    regionAPI.getRegions().forEach(rg -> {

                        regionAPI.getPlayersInRegion(rg).forEach(playerInRegion -> {

                            RegionMapData.regionMap.put(player, regionAPI.getName(rg));

                            player.stopSound(bgm.getString("bgm." + regionAPI.getName(rg) + ".bgm"));
                            playerInRegion.playSound(playerInRegion.getLocation(),
                                    bgm.getString("bgm." + regionAPI.getName(rg) + ".bgm"),
                                    bgm.getFloat("bgm." + regionAPI.getName(rg) + ".volume"),
                                    bgm.getFloat("bgm." + regionAPI.getName(rg) + ".pitch"));
                        });
                    });
                    break;
                }
            });
        }
    }


    @Override
    public void onDisable() {
        Config bgm = new Config("bgm", RegionBGMMain.getPlugin());

        if (bgm.getConfig().getConfigurationSection("bgm") != null) {
            bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {
                ConfigurationSection section = bgm.getConfig().getConfigurationSection("bgm." + key);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.stopSound(section.getString("bgm"));
                }
            });
        }
    }

    public static RegionBGMMain getPlugin() {
        return plugin;
    }
}
