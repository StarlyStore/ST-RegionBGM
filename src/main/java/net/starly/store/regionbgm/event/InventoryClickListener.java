package net.starly.store.regionbgm.event;

import net.starly.core.data.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.starly.store.regionbgm.RegionBGM.plugin;

public class InventoryClickListener implements Listener {


    /**
     * 플레이어가 인벤토리를 클릭했을 때 토글합니다.
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        Config config = new Config("config", plugin);
        if(event.getView().getTitle().equals(config.getConfig().getString("gui_settings.title"))) {
            event.setCancelled(true);
            player.closeInventory();

            Config data = new Config("data/" + player.getUniqueId(), plugin);
            if(data.getBoolean("toggle")) {
                data.setBoolean("toggle", false);
            } else {
                data.setBoolean("toggle", true);
            }
        }
    }
}
