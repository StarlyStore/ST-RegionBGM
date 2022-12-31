package net.starly.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.region.events.RegionLeaveEvent;
import net.starly.regionbgm.data.RegionMapData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static net.starly.regionbgm.RegionBGM.plugin;

public class RegionLeaveListener implements Listener {


    /**
     * 플레이어가 구역에서 나갔을 때 구역브금을 멈춥니다.
     *
     * @param event RegionLeaveEvent
     */
    @EventHandler
    public void leaveRegion(@NotNull RegionLeaveEvent event) {
        Player player = event.getPlayer();

        Config config = new Config("bgm", plugin);
        config.loadDefaultConfig();
        ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + event.getName());


        if (section != null) {
            String name = event.getName();

            player.stopSound(config.getString("bgm." + name + ".bgm"));

            if (!RegionMapData.taskIdMap.containsKey(name + " " + player.getUniqueId())) return;

            Bukkit.getScheduler().cancelTask(RegionMapData.taskIdMap.get(name + " " + player.getUniqueId()));
            RegionMapData.taskIdMap.remove(name + " " + player.getUniqueId());
        }
    }
}
