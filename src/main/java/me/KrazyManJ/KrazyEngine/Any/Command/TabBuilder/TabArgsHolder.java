package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@FunctionalInterface
public interface TabArgsHolder {
    @NotNull List<String> get(@NotNull CommandSender tabPlayer);
}
