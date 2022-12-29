package net.starly.regionbgm.data;

import net.starly.core.data.Config;
import net.starly.regionbgm.RegionBGM;
import org.bukkit.entity.Player;

public class StringData {

    private Config message = new Config("message", RegionBGM.plugin);


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

    public String msgCompleteCreate() {
        return getPrefix() + message.getString("msg.complete_create");
    }

    public String msgCompleteRemove() {
        return getPrefix() + message.getString("msg.complete_remove");
    }

    public String errMsgCreateNoExistRegion() {
        return getPrefix() + message.getString("errMsg.create.noExistRegion");
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

    public String errMsgCreateTypingRegionName() {
        return getPrefix() + message.getString("errMsg.create.typingRegionName");
    }

    public String errMsgCreateTypingBGMName() {
        return getPrefix() + message.getString("errMsg.create.typingBGMName");
    }

    public String errMsgCreateTypingLength() {
        return getPrefix() + message.getString("errMsg.create.typingBGMLength");
    }

    public String errMsgCreateTypingVolume() {
        return getPrefix() + message.getString("errMsg.create.typingBGMVolume");
    }

    public String errMsgCreateTypingPitch() {
        return getPrefix() + message.getString("errMsg.create.typingBGMPitch");
    }

    public String errMsgCreateTypingLoop() {
        return getPrefix() + message.getString("errMsg.create.typingBGMLoop");
    }

    public String errMsgCreateAlreadyExist() {
        return getPrefix() + message.getString("errMsg.create.alreadyExist");
    }

    public String errMsgNotExistRegion() {
        return getPrefix() + message.getString("errMsg.notExistRegion");
    }

    public String errMsgNoPermission() {
        return getPrefix() + message.getString("errMsg.noPermission");
    }


    public String getPrefix() {
        return message.getString("prefix");
    }

}
