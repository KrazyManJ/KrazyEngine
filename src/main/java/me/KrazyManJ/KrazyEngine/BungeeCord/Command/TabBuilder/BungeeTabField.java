package me.KrazyManJ.KrazyEngine.BungeeCord.Command.TabBuilder;

import me.KrazyManJ.KrazyEngine.Core.Command.TabBuilder.TabField;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class BungeeTabField extends TabField<BungeeTabField, CommandSender> {

    public BungeeTabField(@NotNull List<String> args) {
        super(args);
    }

    public BungeeTabField(@NotNull List<String> args, @NotNull Predicate<CommandSender> predicate) {
        super(args, predicate);
    }

    public BungeeTabField(@NotNull Function<CommandSender, List<String>> args) {
        super(args);
    }

    public BungeeTabField(@NotNull Function<CommandSender, List<String>> args, @NotNull Predicate<CommandSender> predicate) {
        super(args, predicate);
    }

    public BungeeTabField(@NotNull String arg) {
        super(arg);
    }

    public BungeeTabField(@NotNull String arg, @NotNull Predicate<CommandSender> predicate) {
        super(arg, predicate);
    }

    public BungeeTabField(@NotNull String arg, String... oArgs) {
        super(arg, oArgs);
    }
}
