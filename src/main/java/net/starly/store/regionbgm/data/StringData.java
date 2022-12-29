package net.starly.store.regionbgm.data;

import net.starly.core.data.Config;
import org.bukkit.entity.Player;

import static net.starly.store.regionbgm.RegionBGM.plugin;

public class StringData {

    private Config message = new Config("message", plugin);


    public String msgHelp(Player player) {

        for(String list : Translate.color(message.getStringList("msg.help"))) {
            player.sendMessage(list);
        }
        return null;
    }

    public String msgToggledOn() {
        return getPrefix() + message.getString("msg.toggled_on");
    }

    public String msgToggledOff() {
        return getPrefix() + message.getString("msg.toggled_off");
    }

    public String msgCancelChangeBGM() {
        return getPrefix() + message.getString("msg.cancelChangeBGM");
    }

    public String msgCancelChangeLength() {
        return getPrefix() + message.getString("msg.cancelChangeLength");
    }

    public String msgTypingChangeBGMName() {
        return getPrefix() + message.getString("msg.typing_changeBGMName");
    }

    public String msgTypingChangeBGMLength() {
        return getPrefix() + message.getString("msg.typing_changeBGMLength");
    }

    public String msgCompleteChangeBGMName() {
        return getPrefix() + message.getString("msg.complete_changeBGMName");
    }

    public String msgCompleteChangeBGMLength() {
        return getPrefix() + message.getString("msg.complete_changeBGMLength");
    }

    public String errMsgNumberInvalid() {
        return getPrefix() + message.getString("errMsg.numberInvalid");
    }

    public String errMsgTypingEditRegionName() {
        return getPrefix() + message.getString("errMsg.typingEditRegionName");
    }

    public String errMsgTypingDelRegionName() {
        return getPrefix() + message.getString("errMsg.typingDelRegionName");
    }

    public String errMsgInvalidCommand() {
        return getPrefix() + message.getString("errMsg.invalidCommand");
    }


    public String getPrefix() {
        return message.getString("prefix");
    }

}
