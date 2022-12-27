package net.starly.store.regionbgm.data;

import net.starly.core.data.util.Tuple;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RegionMapData {

    public static Map<Player, String> regionMap = new HashMap<>();
    public static Map<Player, Integer> taskIdMap = new HashMap<>();
    public static Map<Player, String> changeBgmMap = new HashMap<>();
    public static Map<Player, GuiEditor> guiType = new HashMap<>();

}
