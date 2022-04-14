package me.KrazyManJ.KrazyEngine.Any.Component;

import me.KrazyManJ.KrazyEngine.Any.Text.ColorUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public final class ComponentMaker {

    @Deprecated private ComponentMaker() {}
    
    public static BaseComponent[] hoverText(String text, String hover){
        return new ComponentBuilder(ComponentUtils.toSingle(TextComponent.fromLegacyText(ColorUtils.colorizeHex(text))))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text(TextComponent.fromLegacyText(ColorUtils.colorizeHex(hover)))))
                .create();
    }
}
