package net.starly.regionbgm;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.regionbgm.data.RegionMapData;
import net.starly.regionbgm.event.*;
import net.starly.regionbgm.commands.BGMCmd;
import net.starly.regionbgm.commands.ToggleCmd;
import net.starly.regionbgm.commands.tabcomplete.BgmTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class RegionBGM extends JavaPlugin {


    public static RegionBGM plugin;
    private static Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        plugin = this;
        init();
    }


    @Override
    public void onDisable() {
        Config bgm = new Config("bgm", plugin);

        if (bgm.getConfig().getConfigurationSection("bgm") != null) {
            bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {
                ConfigurationSection section = bgm.getConfig().getConfigurationSection("bgm." + key);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.stopSound(section.getString("bgm"));
                }
            });
        }
    }


    /**
     * 플러그인 정보를 로드합니다. (플러그인이 활성화될 때)
     */
    public void init() {
        if(Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            log.warning("[" + plugin.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttps://discord.gg/TF8jqSJjCG");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else if(Bukkit.getPluginManager().getPlugin("ST-Region") == null) {
            log.warning("[" + plugin.getName() + "] ST-Region 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttps://discord.gg/TF8jqSJjCG");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }


        new Metrics(this, 17241);


        // CONFIG
        Config bgm = new Config("bgm", this);


        // EVENT LISTENER
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionEnterListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);


        // COMMAND
        Bukkit.getPluginCommand("bgm").setExecutor(new ToggleCmd());
        Bukkit.getPluginCommand("regionbgm").setExecutor(new BGMCmd());
        Bukkit.getPluginCommand("regionbgm").setTabCompleter(new BgmTabComplete());


        // PlaySound
        if (bgm.getConfig().getConfigurationSection("bgm") != null) {
            bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("regionbgm.bgm." + key)) {

                        RegionAPI regionAPI = new RegionAPI(plugin);

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
                }
            });
        }
    }
}
