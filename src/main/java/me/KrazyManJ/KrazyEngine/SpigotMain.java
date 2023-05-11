package me.KrazyManJ.KrazyEngine;


import me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder.SpigotTabBuilder;
import me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder.SpigotTabField;
import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public final class SpigotMain extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        CommandMapRegistry.register(new Command("tabtest") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                return false;
            }

            @NotNull
            @Override
            public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return new SpigotTabBuilder().fields(
                        new SpigotTabField("yes").fields(new SpigotTabField("ez"))
                ).build(sender,args);
            }
        });
    }

    @Override
    public void onDisable() { super.onDisable(); }
}
