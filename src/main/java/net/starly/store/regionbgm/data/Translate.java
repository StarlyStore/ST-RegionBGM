package net.starly.store.regionbgm.data;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Translate {


    /**
     * String List의 색을 변경합니다.
     * @param list String List
     * @return String List
     */
    protected static List<String> color(List<String> list) {
        List<String> newList = new ArrayList<>();
        for(String str : list) {
            str = ChatColor.translateAlternateColorCodes('&', str);
            newList.add(str);
        }
        return newList;
    }
}
