package net.starly.store.regionbgm.data;

import net.starly.core.data.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import static net.starly.store.regionbgm.RegionBGM.plugin;

public class RegionBGMObj {

    private Player player;


    public RegionBGMObj(Player player) {
        this.player = player;
    }


    /**
     * 플레이어의 토글 정보를 생성합니다.
     */
    public void createPlayerData() {
        Config data = new Config("data/" + player.getUniqueId(), plugin);

        if(!data.isFileExist()) {
            data.setBoolean("toggle", true);
        }
    }


    /**
     * 구역브금을 생성합니다.
     * @param region 구역 이름
     * @param bgm 브금 이름
     * @param length 브금 길이
     * @param volume 브금 불륨
     * @param pitch 브금 높낮이
     * @param loop 브금 반복 여부
     */
    public void createRegionBGM(String region, String bgm, Integer length, Integer volume, Integer pitch, Boolean loop) {
        Config config = new Config("bgm/" + region, plugin);
        ConfigurationSection section = config.getConfig().getConfigurationSection(region);

        if(section == null) {
            section.getConfigurationSection("bgm").set(region + ".bgm", bgm);
            section.getConfigurationSection("bgm").set(region + ".length", length);
            section.getConfigurationSection("bgm").set(region + ".volume", volume);
            section.getConfigurationSection("bgm").set(region + ".pitch", pitch);
            section.getConfigurationSection("bgm").set(region + ".loop", loop);
        }
    }


    /**
     * 구역브금을 삭제합니다.
     * @param region 구역 이름
     */
    public void removeRegionBGM(String region) {
        Config config = new Config("bgm/" + region, plugin);
        ConfigurationSection section = config.getConfig().getConfigurationSection(region);

        if(section != null) {
            config.getConfig().set(region, null);
        }
    }
}
