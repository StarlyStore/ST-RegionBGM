package net.starly.store.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.region.events.RegionEnterEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static net.starly.store.regionbgm.RegionBGM.plugin;
import static net.starly.store.regionbgm.data.RegionMapData.taskIdMap;


public class RegionEnterListener implements Listener {


    /**
     * 플레이어가 구역에 들어갔을 때 구역브금을 재생합니다.
     *
     * @param event RegionEnterEvent
     */
    @EventHandler
    public void onEntered(@NotNull RegionEnterEvent event) {
        Player player = event.getPlayer();
        Config data = new Config("data/" + player.getUniqueId(), plugin);
        data.loadDefaultConfig();
        if (!data.getBoolean("toggle")) return;

        Config config = new Config("bgm", plugin);
        config.loadDefaultConfig(); //loadDefaultConfig -> 파일 불러옴. loadDefaultPluginConfig -> 플러그인 파일 없으면 저장하고 불러옴.
        ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + event.getName());


        if (section != null) {
            String name = event.getName();

            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.stopSound(config.getConfig().getString("bgm." + name + ".bgm"));
                    player.playSound(player.getLocation(), config.getString("bgm." + name + ".bgm"),
                            config.getFloat("bgm." + name + ".volume"), config.getFloat("bgm." + name + ".pitch"));
                }
            };

            if (config.getBoolean("bgm." + name + ".loop")) {

                int taskId = task.runTaskTimerAsynchronously(plugin, 0, config.getInt("bgm." + name + ".length") * 20).getTaskId();
                taskIdMap.put(name + " " + player.getUniqueId(), taskId);

            } else {
                task.runTaskAsynchronously(plugin);
            }
        }
    }
}
