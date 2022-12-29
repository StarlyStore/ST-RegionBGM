package net.starly.store.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.store.regionbgm.data.GuiEditor;
import net.starly.store.regionbgm.data.RegionBGMGuiEditorObj;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import static net.starly.store.regionbgm.RegionBGM.messageConfig;
import static net.starly.store.regionbgm.RegionBGM.plugin;
import static net.starly.store.regionbgm.data.RegionMapData.*;

public class InventoryClickListener implements Listener {


    /**
     * 플레이어가 인벤토리를 클릭했을 때 토글합니다.
     *
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onToggle(@NotNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        Config config = new Config("config", plugin);
        if (event.getView().getTitle().equals(config.getString("toggled_gui_settings.title"))) {
            if (slot == config.getInt("toggled_gui_settings.toggled_on.slot")) {
                event.setCancelled(true);
                player.closeInventory();

                Config data = new Config("data/" + player.getUniqueId(), plugin);

                if (data.getBoolean("toggle")) {
                    data.setBoolean("toggle", false);
                    player.sendMessage(messageConfig.getMessage("messages.toggled_off"));
                } else {
                    data.setBoolean("toggle", true);
                    player.sendMessage(messageConfig.getMessage("messages.toggled_on"));
                }
            }
            event.setCancelled(true);
        }
    }


    /**
     * 구역브금 편집기 - status, bgmName, volume, pitch, loop
     *
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onEditor(@NotNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        RegionBGMGuiEditorObj regionBGMGuiEditorObj = new RegionBGMGuiEditorObj(player);

        Config config = new Config("config", plugin);
        if (event.getView().getTitle().equals(config.getString("edit_gui_settings.title"))) {

            if (slot == config.getInt("edit_gui_settings.bgmName.slot")) {

                changeBgmMap.put(player, regionMap.get(player));
                guiType.put(player, GuiEditor.BGM);
                player.sendMessage("§a변경할 구역브금을 채팅에 입력해주세요.");
                player.closeInventory();
            } else if (slot == config.getInt("edit_gui_settings.length.slot")) {
                changeBgmMap.put(player, regionMap.get(player));
                guiType.put(player, GuiEditor.LENGTH);

                player.sendMessage("§a변경할 구역브금 재생시간을 채팅에 입력해주세요.");
                player.closeInventory();
            } else if (slot == config.getInt("edit_gui_settings.volume.slot")) {
                regionBGMGuiEditorObj.changeBGMVolume(event);
            } else if (slot == config.getInt("edit_gui_settings.pitch.slot")) {
                regionBGMGuiEditorObj.changeBGMPitch(event);
            } else if (slot == config.getInt("edit_gui_settings.loop.true.slot")) {
                regionBGMGuiEditorObj.changeBGMLoop();
            } else if (slot == config.getInt("edit_gui_settings.loop.false.slot")) {
                regionBGMGuiEditorObj.changeBGMLoop();
            }
            event.setCancelled(true);
        }
    }
}
