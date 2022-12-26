package net.starly.store.regionbgm.data;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RegionMapData {

    protected static Map<String, String> bgmMap = new HashMap<>();
    protected static Map<Player, GuiEditor> guiEditorMap = new HashMap<>();
    public static Map<Player, Integer> taskIdMap = new HashMap<>();
}
