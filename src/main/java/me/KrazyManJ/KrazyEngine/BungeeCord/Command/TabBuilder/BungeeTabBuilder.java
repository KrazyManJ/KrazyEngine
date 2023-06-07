package me.KrazyManJ.KrazyEngine.BungeeCord.Command.TabBuilder;

import me.KrazyManJ.KrazyEngine.Core.Command.TabBuilder.TabBuilder;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public final class BungeeTabBuilder extends TabBuilder<BungeeTabBuilder, BungeeTabField, CommandSender> {


    public BungeeTabBuilder(@NotNull List<String> defaultInsert) {
        super(defaultInsert);
    }
}
