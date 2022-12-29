package net.starly.store.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.region.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static net.starly.store.regionbgm.RegionBGM.plugin;
import static net.starly.store.regionbgm.data.RegionMapData.*;

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


            player.stopSound(config.getConfig().getString("bgm." + name + ".bgm"));

            if (!taskIdMap.containsKey(name)) return;

            Bukkit.getScheduler().cancelTask(taskIdMap.get(name).getB());
            taskIdMap.remove(name);
        }
    }
}
