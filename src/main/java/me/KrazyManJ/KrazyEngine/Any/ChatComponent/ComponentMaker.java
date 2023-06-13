package me.KrazyManJ.KrazyEngine.Any.ChatComponent;

import me.KrazyManJ.KrazyEngine.Any.ChatComponent.Target.TargetSelectorBuilder;
import me.KrazyManJ.KrazyEngine.Any.ChatComponent.Translate.KeybindID;
import me.KrazyManJ.KrazyEngine.Any.Text.ColorTranslator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("unused")
public class ComponentMaker {

    protected ComponentMaker() {
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull ChatColor c) {
        return new TranslatableComponent("color.minecraft." + c.toString().toLowerCase());
    }

    @Contract("_ -> new")
    public static @NotNull KeybindComponent makeKeybind(@NotNull KeybindID id) {
        return new KeybindComponent(id.getId());
    }

    @Contract("_ -> new")
    public static @NotNull SelectorComponent makeTarget(@NotNull TargetSelectorBuilder builder) {
        return new SelectorComponent(builder.build());
    }

    public static @NotNull TextComponent makeText(String text) {
        return (TextComponent) toSingle(TextComponent.fromLegacyText(ColorTranslator.formatEverything(text)));
    }

    public static BaseComponent toSingle(BaseComponent[] components){
        BaseComponent rc = new TextComponent("");
        for (BaseComponent c : components) rc.addExtra(c);
        return rc;
    }

    public static BaseComponent[] toArray(BaseComponent component){
        return new BaseComponent[]{component};
    }
}
