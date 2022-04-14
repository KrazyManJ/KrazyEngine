package me.KrazyManJ.KrazyEngine.Any.Component;

import me.KrazyManJ.KrazyEngine.Any.Component.TranslateEnums.KeybindID;
import net.md_5.bungee.api.chat.KeybindComponent;

public final class KeybindMaker {

    @Deprecated private KeybindMaker() {}
    
    public static KeybindComponent makeKey(KeybindID id){
        return new KeybindComponent(id.getId());
    }
}
