package net.starly.store.regionbgm.event;

import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {


    @EventHandler
    public void editBGMLength(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);

        regionBGMGuiEditorObj.editBGMLength(event);

    }
}
