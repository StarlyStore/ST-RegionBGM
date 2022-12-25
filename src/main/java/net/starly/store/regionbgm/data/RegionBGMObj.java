package net.starly.store.regionbgm.data;

import net.starly.core.data.Config;
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
}
