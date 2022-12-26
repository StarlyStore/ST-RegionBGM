package net.starly.store.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.region.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.starly.store.regionbgm.RegionBGM.plugin;
import static net.starly.store.regionbgm.data.RegionMapData.taskIdMap;

public class RegionLeaveListener implements Listener {


    @EventHandler
    public void leaveRegion(RegionLeaveEvent event) {
        Player player = event.getPlayer();
        Config data = new Config("data/" + player.getUniqueId(), plugin);
        data.loadDefaultConfig();
        if (!data.getBoolean("toggle")) return;

        Config config = new Config("bgm", plugin);
        ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + event.getName());

        if (section != null) {
            String name = event.getName();

            player.stopSound(config.getConfig().getString("bgm." + name + ".bgm"));

            if (taskIdMap.containsKey(player)) {
                Bukkit.getScheduler().cancelTask(taskIdMap.get(player));
                taskIdMap.remove(player);
            }
        }
    }
}
