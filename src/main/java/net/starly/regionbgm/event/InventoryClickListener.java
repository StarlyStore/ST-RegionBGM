package net.starly.regionbgm.event;

import net.starly.core.data.Config;
import net.starly.regionbgm.RegionBGMMain;
import net.starly.regionbgm.data.GuiEditor;
import net.starly.regionbgm.data.RegionMapData;
import net.starly.regionbgm.data.RegionBGMGuiEditorObj;
import net.starly.regionbgm.data.StringData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

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

        Config config = new Config("config", RegionBGMMain.getPlugin());
        if (event.getView().getTitle().equals(config.getString("toggled_gui_settings.title"))) {
            if (slot == config.getInt("toggled_gui_settings.toggled_on.slot")) {
                event.setCancelled(true);
                player.closeInventory();

                Config data = new Config("data/" + player.getUniqueId(), RegionBGMMain.getPlugin());
                StringData stringData = new StringData();

                if (data.getBoolean("toggle")) {
                    data.setBoolean("toggle", false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgToggledOff()));
                } else {
                    data.setBoolean("toggle", true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgToggledOn()));
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
        StringData stringData = new StringData();

        Config config = new Config("config", RegionBGMMain.getPlugin());
        if (event.getView().getTitle().equals(config.getString("edit_gui_settings.title"))) {

            if (slot == config.getInt("edit_gui_settings.bgmName.slot")) {

                RegionMapData.changeBgmMap.put(player, RegionMapData.regionMap.get(player));
                RegionMapData.guiType.put(player, GuiEditor.BGM);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgTypingChangeBGMName()));
                player.closeInventory();
            } else if (slot == config.getInt("edit_gui_settings.length.slot")) {
                RegionMapData.changeBgmMap.put(player, RegionMapData.regionMap.get(player));
                RegionMapData.guiType.put(player, GuiEditor.LENGTH);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgTypingChangeBGMLength()));
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
