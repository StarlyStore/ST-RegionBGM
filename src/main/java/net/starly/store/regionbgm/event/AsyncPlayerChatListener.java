package net.starly.store.regionbgm.event;

import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerChatListener implements Listener {


    @EventHandler
    public void editBGMLength(@NotNull AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);

        regionBGMGuiEditorObj.changeBGMLength(event);

    }


    @EventHandler
    public void editBGMName(@NotNull AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);
        regionBGMGuiEditorObj.changeBGM(event);
    }
}
