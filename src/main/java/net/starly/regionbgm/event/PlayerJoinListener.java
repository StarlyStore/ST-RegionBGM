package net.starly.regionbgm.event;

import net.starly.regionbgm.data.RegionBGMObj;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener implements Listener {


    /**
     * 플레이어가 접속했을 때 toggle 정보를 생성합니다.
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RegionBGMObj regionBGMObj = new RegionBGMObj(player);
        regionBGMObj.createPlayerData();
    }
}
