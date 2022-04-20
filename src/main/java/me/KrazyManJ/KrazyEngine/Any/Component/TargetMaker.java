package me.KrazyManJ.KrazyEngine.Any.Component;

import me.KrazyManJ.KrazyEngine.Any.Component.Target.TargetBuilder;
import net.md_5.bungee.api.chat.SelectorComponent;

@SuppressWarnings("unused")
public final class TargetMaker {
    @Deprecated private TargetMaker() {}

    public SelectorComponent makeTarget(TargetBuilder builder){
        return new SelectorComponent(builder.build());
    }
}
