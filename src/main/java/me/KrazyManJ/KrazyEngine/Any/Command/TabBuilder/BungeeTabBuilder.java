package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public final class BungeeTabBuilder extends TabBuilder<BungeeTabBuilder,BungeeTabField, CommandSender>{
    public BungeeTabBuilder() {}
    public BungeeTabBuilder(@NotNull List<String> defaultInsert) { super(defaultInsert); }
}
