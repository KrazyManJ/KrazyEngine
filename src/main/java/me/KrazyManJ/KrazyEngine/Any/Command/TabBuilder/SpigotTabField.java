package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class SpigotTabField extends TabField<SpigotTabField, CommandSender> {
    public SpigotTabField(@NotNull List<String> args) {
        super(args);
    }
    public SpigotTabField(@NotNull List<String> args, @NotNull Predicate<CommandSender> predicate) {
        super(args, predicate);
    }
    public SpigotTabField(@NotNull Function<CommandSender, List<String>> args) {
        super(args);
    }
    public SpigotTabField(@NotNull Function<CommandSender, List<String>> args, @NotNull Predicate<CommandSender> predicate) {
        super(args, predicate);
    }
    public SpigotTabField(@NotNull String arg) {
        super(arg);
    }
    public SpigotTabField(@NotNull String arg, @NotNull Predicate<CommandSender> predicate) {
        super(arg, predicate);
    }
    public SpigotTabField(@NotNull String arg, String... oArgs) {
        super(arg, oArgs);
    }
}
