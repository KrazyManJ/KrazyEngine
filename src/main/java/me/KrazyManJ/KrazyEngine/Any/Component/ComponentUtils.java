package me.KrazyManJ.KrazyEngine.Any.Component;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

@SuppressWarnings("unused")
public final class ComponentUtils {

    @Deprecated private ComponentUtils() {}
    
    public static BaseComponent toSingle(BaseComponent[] components){
        BaseComponent rc = new TextComponent("");
        for (BaseComponent c : components) rc.addExtra(c);
        return rc;
    }
    public static BaseComponent[] toArray(BaseComponent component){
        return new BaseComponent[]{component};
    }
}
