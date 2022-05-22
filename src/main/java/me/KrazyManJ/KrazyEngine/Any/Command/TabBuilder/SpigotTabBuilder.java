package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public final class SpigotTabBuilder extends TabBuilder<SpigotTabBuilder,SpigotTabField, CommandSender>{
    public SpigotTabBuilder() {

    }
    public SpigotTabBuilder(@NotNull List<String> defaultInsert) {
        super(defaultInsert);
    }
}
