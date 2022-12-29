package net.starly.store.regionbgm.event;

import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerChatListener implements Listener {


    /**
     * 플레이어가 채팅을 입력했을 때 브금 길이를 변경합니다.
     * @param event AsyncPlayerChatEvent
     */
    @EventHandler
    public void editBGMLength(@NotNull AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);

        regionBGMGuiEditorObj.changeBGMLength(event);
    }


    /**
     * 플레이어가 채팅 입력을 했을 때 브금 이름을 변경합니다.
     * @param event
     */
    @EventHandler
    public void editBGMName(@NotNull AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);
        regionBGMGuiEditorObj.changeBGM(event);
    }
}
