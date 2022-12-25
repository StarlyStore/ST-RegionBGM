package net.starly.store.regionbgm.event;

import de.netzkronehd.wgregionevents.events.RegionEnteredEvent;
import net.starly.core.data.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RegionEnteredListener implements Listener {


    @EventHandler
    public void onEntered(RegionEnteredEvent event) {

        if(event.getRegion().getId().equalsIgnoreCase("")) {

        }
    }
}
