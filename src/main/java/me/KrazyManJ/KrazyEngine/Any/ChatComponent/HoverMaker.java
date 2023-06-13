package me.KrazyManJ.KrazyEngine.Any.ChatComponent;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "ConstantConditions"})
public class HoverMaker {

    protected HoverMaker() {
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromText(String text) {
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(TextComponent.fromLegacyText(text)));
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromText(BaseComponent[] text) {
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(text));
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromItem(@NotNull Item item) {
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM, item);
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromEntity(@NotNull Entity entity) {
        return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, entity);
    }


}
