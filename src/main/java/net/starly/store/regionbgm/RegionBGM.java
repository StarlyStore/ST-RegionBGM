package net.starly.store.regionbgm;

import net.starly.core.data.Config;
import net.starly.store.regionbgm.commands.BGMCmd;
import net.starly.store.regionbgm.commands.ToggleCmd;
import net.starly.store.regionbgm.commands.tabcomplete.BgmTabComplete;
import net.starly.store.regionbgm.data.RegionMapData;
import net.starly.store.regionbgm.event.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class RegionBGM extends JavaPlugin {


    public static RegionBGM plugin;


    @Override
    public void onEnable() {
        plugin = this;
        init();
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

        bgm.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {
            RegionMapData.taskIdMap.put(key, new HashMap<>());
        });
    }
}
