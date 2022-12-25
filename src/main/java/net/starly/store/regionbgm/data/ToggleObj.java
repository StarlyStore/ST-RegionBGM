package net.starly.store.regionbgm.data;

import net.starly.core.data.Config;
import net.starly.store.regionbgm.RegionBGM;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ToggleObj {


    private Player player;



    public ToggleObj(Player player) {
        this.player = player;
    }


    /**
     * 토글 GUI를 엽니다.
     */
    public void toggleGUI() {
        Config toggled = new Config("config", RegionBGM.plugin);

        Inventory inv = Bukkit.createInventory(null,
                toggled.getConfig().getInt("gui_settings.size") * 9, toggled.getConfig().getString("gui_settings.title"));


        Config data = new Config("data/" + player.getUniqueId(), RegionBGM.plugin);

        if(!data.getBoolean("toggle")) {
            onToggled(inv);
        } else {
            offToggled(inv);
        }
        player.openInventory(inv);
    }


    /**
     * 플레이어의 토글이 켜져있을 때
     */
    private void onToggled(Inventory inv) {
        Config config = new Config("config", RegionBGM.plugin);

        String name = ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("gui_settings.toggled_on.name"));
        String material = config.getConfig().getString("gui_settings.toggled_on.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("gui_settings.toggled_on.lore"));
        int slot = config.getConfig().getInt("gui_settings.toggled_on.slot");

        setItem(inv, name, material, lore, slot);
    }


    /**
     * 플레이어의 토글이 꺼져있을 때
     */
    private void offToggled(Inventory inv) {
        Config config = new Config("config", RegionBGM.plugin);

        String name =  ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("gui_settings.toggled_off.name"));
        String material = config.getConfig().getString("gui_settings.toggled_off.material");
        List<String> lore = Translate.color(config.getConfig().getStringList("gui_settings.toggled_off.lore"));
        int slot = config.getConfig().getInt("gui_settings.toggled_off.slot");

        setItem(inv, name, material, lore, slot);
    }


    /**
     * ItemStack을 저장합니다.
     * @param inv 인벤토리
     * @param name 아이템 이름
     * @param material 아이템 종류
     * @param lore 아이템 로어
     * @param slot 아이템 슬롯
     */
    private void setItem(Inventory inv, String name, String material, List<String> lore, int slot) {

        ItemStack itemStack = new ItemStack(Material.valueOf(material));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inv.setItem(slot, itemStack);
    }
}
