package net.starly.regionbgm.data;

import net.starly.core.data.Config;
import net.starly.region.api.RegionAPI;
import net.starly.regionbgm.RegionBGMMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class RegionBGMObj {

    private Player player;


    public RegionBGMObj(Player player) {
        this.player = player;
    }


    /**
     * 플레이어의 토글 정보를 생성합니다.
     */
    public void createPlayerData() {
        Config data = new Config("data/" + player.getUniqueId(), RegionBGMMain.getPlugin());

        if (!data.isFileExist()) {
            data.setBoolean("toggle", true);
        }
    }


    /**
     * 구역브금을 생성합니다.
     *
     * @param region  구역 이름
     * @param bgmName 브금 이름
     * @param length  브금 길이
     * @param volume  브금 불륨
     * @param pitch   브금 높낮이
     * @param loop    브금 반복 여부
     */
    public void createRegionBGM(String region, String bgmName, Integer length, Integer volume, Integer pitch, Boolean loop) {
        Config config = new Config("bgm", RegionBGMMain.getPlugin());
        config.loadDefaultConfig();

        StringData stringData = new StringData();
        ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + region);

        RegionAPI regionAPI = new RegionAPI(RegionBGMMain.getPlugin());
        if (regionAPI.getRegion(region) == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgCreateNoExistRegion()));
            return;
        }


        if (section == null) {
            config.getConfig().createSection("bgm." + region);
            section = config.getConfig().getConfigurationSection("bgm." + region);

            section.set("bgm", bgmName);
            section.set("length", length);
            section.set("volume", volume);
            section.set("pitch", pitch);
            section.set("loop", loop);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgCompleteCreate()
                    .replace("{region}", region)));
            config.saveConfig();
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgCreateAlreadyExist()));
        }
    }


    /**
     * 구역브금을 삭제합니다.
     *
     * @param region 구역 이름
     */
    public void removeRegionBGM(String region) {
        Config config = new Config("bgm", RegionBGMMain.getPlugin());

        StringData stringData = new StringData();

        ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + region);

        if (section == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgNotExistRegion()));
            return;
        }

        config.setObject("bgm." + region, null);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgCompleteRemove()
                .replace("{region}", region)));
        config.saveConfig();
    }


    public void showRegionBGMList() {
        Config config = new Config("bgm", RegionBGMMain.getPlugin());
        config.loadDefaultConfig();

        player.sendMessage("§8■ §7══════°• §8[ §6구역브금 §f목록 §8] §7•°══════ §8■");

        config.getConfig().getConfigurationSection("bgm").getKeys(false).forEach(key -> {
            ConfigurationSection section = config.getConfig().getConfigurationSection("bgm." + key);

            if (section != null) {
                player.sendMessage("§8■ §6>> §7" + key + " §8: §f" + section.getString("bgm"));
            }
        });
    }
}
