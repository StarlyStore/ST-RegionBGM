package net.starly.store.regionbgm.data;

import net.starly.core.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static net.starly.store.regionbgm.RegionBGM.plugin;
import static net.starly.store.regionbgm.data.RegionMapData.guiEditorMap;

public class RegionBGMGuiEditorObj {

    private Player player;
    private String name;


    public RegionBGMGuiEditorObj(Player player) {
        this.player = player;
    }

    public RegionBGMGuiEditorObj(String name, Player player) {
        this.name = name;
        this.player = player;
    }


    /**
     * 구역브금 편집기를 엽니다.
     */
    public void openBGMEditor(String region) {
        Config config = new Config("config", plugin);
        Inventory inv = Bukkit.createInventory(null,
                config.getConfig().getInt("edit_gui_settings.size") * 9,
                config.getConfig().getString("edit_gui_settings.title") + " " + region);

        statusEditor(inv);
        lengthEditor(inv);
        volumeEditor(inv);
        pitchEditor(inv);

        Config bgm = new Config("bgm", plugin);
        ConfigurationSection section = bgm.getConfig().getConfigurationSection("bgm." + region);

        if(section.getBoolean("loop")) {
            loopTrueEditor(inv);
        } else {
            loopFalseEditor(inv);
        }

        player.openInventory(inv);
    }


    /**
     * 구역브금 편집기 - 상태
     *
     * @param inv 편집기 인벤토리
     */
    private void statusEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.status.name"));
        String material = config.getConfig().getString("edit_gui_settings.status.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.status.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.status.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 구역브금 편집기 - 브금 길이
     *
     * @param inv 편집기 인벤토리
     */
    private void lengthEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.length.name"));
        String material = config.getConfig().getString("edit_gui_settings.length.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.length.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.length.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 구역브금 편집기 - 브금 불륨
     *
     * @param inv 편집기 인벤토리
     */
    private void volumeEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.volume.name"));
        String material = config.getConfig().getString("edit_gui_settings.volume.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.volume.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.volume.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 구역브금 편집기 - 브금 높낮이
     *
     * @param inv 편집기 인벤토리
     */
    private void pitchEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.pitch.name"));
        String material = config.getConfig().getString("edit_gui_settings.pitch.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.pitch.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.pitch.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 구역브금 편집기 - 브금 반복 여부 | 켜져있을 때
     * @param inv 편집기 인벤토리
     */
    private void loopTrueEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.loop.true.name"));
        String material = config.getConfig().getString("edit_gui_settings.loop.true.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.loop.true.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.loop.true.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 구역브금 편집기 - 브금 반복 여부 | 꺼져있을 때
     * @param inv 편집기 인벤토리
     */
    private void loopFalseEditor(Inventory inv) {
        Config config = new Config("config", plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("edit_gui_settings.loop.false.name"));
        String material = config.getConfig().getString("edit_gui_settings.loop.false.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("edit_gui_settings.loop.false.lore"));
        int slot = config.getConfig().getInt("edit_gui_settings.loop.false.slot");

        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(inv, name, material, lore, slot);
    }


    /**
     * ItemStack을 저장합니다.
     *
     * @param inv      인벤토리
     * @param name     아이템 이름
     * @param material 아이템 종류
     * @param lore     아이템 로어
     * @param slot     아이템 슬롯
     */
    private void setItem(Inventory inv, String name, String material, List<String> lore, int slot) {

        ItemStack itemStack = new ItemStack(Material.valueOf(material));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inv.setItem(slot, itemStack);
    }


    /**
     * 구역브금 길이를 편집합니다
     *
     * @param event 플레이어가 채팅을 입력했을 때 발생하는 이벤트
     */
    public void editBGMLength(AsyncPlayerChatEvent event) {

        String message = event.getMessage();

        if (guiEditorMap.containsKey(player)) {
            if (guiEditorMap.get(player) == GuiEditor.LENGTH) {
                event.setCancelled(true);
                player.sendMessage("§a구역브금 길이를 " + message + "초로 설정했습니다.");
                guiEditorMap.remove(player);
            }
        }
    }
}
