package net.starly.store.regionbgm;

import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.store.regionbgm.commands.BGMCmd;
import net.starly.store.regionbgm.commands.ToggleCmd;
import net.starly.store.regionbgm.commands.tabcomplete.BgmTabComplete;
import net.starly.store.regionbgm.event.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import static net.starly.store.regionbgm.data.RegionMapData.regionMap;


public class RegionBGM extends JavaPlugin {


    public static RegionBGM plugin;


    @Override
    public void onEnable() {
        plugin = this;
        init();
    }


    @Override
    public void onDisable() {
        Config bgm = new Config("bgm", plugin);

        for (Player player : Bukkit.getOnlinePlayers()) {

            bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {
                ConfigurationSection section = bgm.getConfig().getConfigurationSection("bgm." + key);

                if (section != null) {
                    player.stopSound(section.getString("bgm"));
                }
            });
        }
    }


    /**
     * 플러그인 정보를 로드합니다. (플러그인이 활성화될 때)
     */
    public void init() {

        // EVENT
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionEnterListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegionLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("bgm").setExecutor(new ToggleCmd());
        Bukkit.getPluginCommand("regionbgm").setExecutor(new BGMCmd());
        Bukkit.getPluginCommand("regionbgm").setTabCompleter(new BgmTabComplete());

        // CONFIG
        Config config = new Config("config", this);
        config.loadDefaultPluginConfig();

        Config message = new Config("message", this);
        message.loadDefaultPluginConfig();

        Config bgm = new Config("bgm", this);
        bgm.loadDefaultConfig();


        // PlaySound
        bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("regionbgm.bgm." + key)) {

                    RegionAPI regionAPI = new RegionAPI(plugin);

                    regionAPI.getRegions().forEach(rg -> {

                        regionAPI.getPlayersInRegion(rg).forEach(playerInRegion -> {

                            regionMap.put(player, regionAPI.getName(rg));

                            player.stopSound(bgm.getConfig().getString("bgm." + regionAPI.getName(rg) + ".bgm"));
                            playerInRegion.playSound(playerInRegion.getLocation(),
                                    bgm.getConfig().getString("bgm." + regionAPI.getName(rg) + ".bgm"),
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
