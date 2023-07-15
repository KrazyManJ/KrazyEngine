package me.KrazyManJ.KrazyEngine.Spigot.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author KrazyManJ
 */
@SuppressWarnings("unused")
public final class AliasCommand extends Command {

    private final Function<String[],String> command;
    private BiFunction<CommandSender, String[], List<String>> tab = (sender, args) -> Collections.emptyList();

    public AliasCommand(String alias, Function<String[],String> commandLine){
        super(alias);
        this.command = commandLine;
    }

    public AliasCommand(String alias, Function<String[],String> commandLine, String permission){
        this(alias,commandLine);
        this.setPermission(permission);
    }

    public AliasCommand(String alias, String commandLine){
        this(alias, (args) -> commandLine+" "+String.join(" ",args));
    }

    public AliasCommand(String alias, String commandLine, String permission){
        this(alias, (args) -> commandLine+" "+String.join(" ",args), permission);
    }

    public AliasCommand setTabCompleter(BiFunction<CommandSender, String[], List<String>> completer){
        this.tab = completer;
        return this;
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        Bukkit.dispatchCommand(commandSender, command.apply(strings));
        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return this.tab.apply(sender,args);
    }
}
